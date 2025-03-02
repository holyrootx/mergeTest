package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.GradeDAO;
import sms.student.dao.StudentDAO;
import sms.student.vo.Grade;

public class GradeDeleteService {

	public Grade getDeleteScore(int student_no) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		Grade grade = gradeDAO.selectGrade(student_no);	
		
		con.close();
		return grade;
	}
	
	public boolean deleteGrade(int student_no) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		int deleteCount = gradeDAO.deleteGrade(student_no);
		boolean isDeleteSuccess = false;
		
		if(deleteCount > 0 ) {
			isDeleteSuccess = true;
			con.commit();
		}else {
			con.rollback();
		}
		
		con.close();
		return isDeleteSuccess;
	}
	
}
