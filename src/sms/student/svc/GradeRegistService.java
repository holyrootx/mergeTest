package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.GradeDAO;
import sms.student.dao.StudentDAO;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeRegistService {
		
	public Student getRightStudent(int student_no) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con); // dao 매번 새로 만들기 때문에 con 객체도 새로 생성됨
		
		Student student = studentDAO.selectStudent(student_no);
		con.close();
		
		return student;
	}
	
	public boolean searchGrade(int student_no) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		Grade grade = gradeDAO.selectGrade(student_no);
		boolean isExist = false;
		
		if (grade != null) {
			// grade가 있으면 이미 등록되었다는 뜻이니까. true
			isExist = true;
		}
		
		
		
		con.close();
		
		return isExist;
	}
	
	public boolean registGrade(Grade newGrade) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		boolean isRegistSuccess = false;
		
		int registCount = gradeDAO.insertGrade(newGrade);
		if (registCount > 0) {
			// 0보다 크면 성공
			isRegistSuccess = true;
		}
		con.close();
		return isRegistSuccess;
	}

}
