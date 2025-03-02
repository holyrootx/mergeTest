package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import sms.student.dao.GradeDAO;
import sms.student.dao.StudentDAO;
import sms.student.vo.Grade;

public class GradeSearchService {

	public ArrayList<Grade> getSearchGradeListByStudent_name(String student_name) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		ArrayList<Grade> gradeList = gradeDAO.selectGradeListByStudent_name(student_name);
		
		close(con);
		return gradeList;
		
	}
	
	public ArrayList<Grade> getSearchGradeListByStudent_no(int student_no) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		ArrayList<Grade> gradeList = gradeDAO.selectGradeListByStudent_no(student_no);
		
		close(con);
		
		return gradeList;
	}

	public ArrayList<Grade> getSearchGradeListByStudent_year(int student_year) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		ArrayList<Grade> gradeList = gradeDAO.selectGradeListByStudent_year(student_year);
		
		close(con);
		
		return gradeList;
	}
	
}
