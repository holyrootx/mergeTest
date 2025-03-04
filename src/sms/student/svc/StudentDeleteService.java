package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.StudentDAO;
import sms.student.vo.Student;

public class StudentDeleteService {

	public Student getDeleteStudent(int stu_no) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		Student student = studentDAO.selectStudent(stu_no);
		// �й� ������ ���� ��� �л� ��������
		
		close(con);
		return student;
	}
		
	public boolean deleteStudent(int stu_no) throws Exception{
		Connection con = getConnection();
		StudentDAO memberDAO = new StudentDAO(con);
		
		int deleteCount = memberDAO.deleteStudent(stu_no);
		boolean isDeleteSuccess = false;
		
		if (deleteCount > 0) {
			
			isDeleteSuccess = true;
			commit(con);
			
		} else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}
	
}
