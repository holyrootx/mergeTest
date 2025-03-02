package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.GradeDAO;
import sms.student.vo.Grade;

public class GradeModifyService {

	public Grade getModifyGrade(int student_no) throws Exception {
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		Grade newGrade = gradeDAO.selectGrade(student_no);
		
		con.close();
		
		return newGrade;
	}

	public boolean modifyGrade(Grade changeGrade) throws Exception {
		
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		int updateCount = gradeDAO.updateGrade(changeGrade);
		
		boolean isModifySuccess = false;
		
		if(updateCount > 0) {
			isModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		
		con.close();
		
		return isModifySuccess;
	}
}
