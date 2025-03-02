package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import sms.student.dao.GradeDAO;
import sms.student.dao.ScholarshipDAO;
import sms.student.vo.Scholarship;
import sms.student.vo.ScholarshipStudent;
import sms.student.vo.Grade;

public class ScholarshipStudentSearchService {

	public Scholarship getSearchScholarship(String scholar_name) throws Exception{
		Connection con = getConnection();
		Scholarship scholarship = null;
		
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		scholarship = scholarshipDAO.selectScholarship(scholar_name);
		
		close(con);
		return scholarship;		
	}
	
	public ArrayList<Grade> getScoreListAddPercent() throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		ArrayList<Grade> scoreListAddPercent =  gradeDAO.selectGradeListAddPercent();
		
		close(con);
		return scoreListAddPercent;
	}

	public ArrayList<ScholarshipStudent> getScholarshipStudentSearchList(
			Scholarship searchScholarship, ArrayList<Grade> gradeListAddPercent) throws Exception{
		ArrayList<ScholarshipStudent> ScholarshipStudentList = new ArrayList<>();
		
		// 가져와서 그냥 뽑아내면됨 ㅅㄱ
		float numOfTier = (float)searchScholarship.getScholar_percent()*0.01f;
		// (int)(percent * 10 + 0.5f) / 10f;
		for(Grade grade : gradeListAddPercent) {
			System.out.printf("이름 : %s  퍼센트 : %f  등급 : %f%% %n",grade.getStudent_name(),grade.getPercent(),numOfTier);
			if(grade.getPercent() <= numOfTier) {
				System.out.println("장학금 대상입니다."+"실제 값: "+grade.getPercent()+"등급표 : "+ numOfTier);
				
				ScholarshipStudent scholarshipStudent = new ScholarshipStudent(
																grade.getStudent_no(),
																grade.getStudent_name(),
																grade.getAvg(),
																searchScholarship.getScholar_name(),
																grade.getPercent(),
																searchScholarship.getScholar_money()
															);
				
				ScholarshipStudentList.add(scholarshipStudent);
			}
			
		}
			
		
		return ScholarshipStudentList;
	}
	
}
