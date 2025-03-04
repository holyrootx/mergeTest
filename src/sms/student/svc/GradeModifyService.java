package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import sms.student.dao.GradeDAO;
import sms.student.dao.StudentDAO;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeModifyService {

	public Grade getModifyGrade(int student_no) throws Exception{
		
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		Grade modifyGrade = gradeDAO.selectGrade(student_no);
		close(con);
		return modifyGrade;
		
	}

	public boolean modifyGrade(Grade changeGrade) throws Exception{
		
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);

		int updateCount = gradeDAO.updateGrade(changeGrade);
		if (updateCount > 0) {
			isModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
}
