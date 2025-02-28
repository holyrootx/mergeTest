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
		// 학번 가지고 수정 대상 학생 가져오기
		
		return student;
	}
		
	public boolean deleteStudent(int stu_no) throws Exception{
		Connection con = getConnection();
		StudentDAO memberDAO = new StudentDAO(con);
		
		int deleteCount = memberDAO.deleteStudent(stu_no);
		boolean isDeleteSuccess = false;
		
		if (deleteCount > 0) {
			// 성공시 성공여부 반환하는 변수 값 변경
			isDeleteSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		con.close();
		return isDeleteSuccess;
	}
	
}
