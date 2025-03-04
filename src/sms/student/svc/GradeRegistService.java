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
		StudentDAO studentDAO = new StudentDAO(con); // dao �Ź� ���� ����� ������ con ��ü�� ���� ������
		
		Student student = studentDAO.selectStudent(student_no);
		close(con);
		
		return student;
	}
	
	public boolean searchGrade(int student_no) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		Grade grade = gradeDAO.selectGrade(student_no);
		boolean isExist = false;
		
		if (grade != null) {
			// grade�� ������ �̹� ��ϵǾ��ٴ� ���̴ϱ�. true
			isExist = true;
		}
		
		
		
		close(con);
		
		return isExist;
	}
	
	public boolean registGrade(Grade newGrade) throws Exception{
		Connection con = getConnection();
		GradeDAO gradeDAO = new GradeDAO(con);
		
		boolean isRegistSuccess = false;
		
		int registCount = gradeDAO.insertGrade(newGrade);
		if (registCount > 0) {
			// 0���� ũ�� ����
			isRegistSuccess = true;
		}
		close(con);
		return isRegistSuccess;
	}

}
