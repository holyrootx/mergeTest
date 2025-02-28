package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import sms.student.dao.StudentDAO;
import sms.student.vo.Student;

public class StudentSearchService {

	public ArrayList<Student> getSearchStudentListByStudent_name(String student_name) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		
		ArrayList<Student> studentList = studentDAO.selectStudentListByStudent_name(student_name);
		
		close(con);
		return studentList;
	}
	
	public ArrayList<Student> getSearchStudentListByStudent_no(int student_no) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		
		ArrayList<Student> studentList = studentDAO.selectStudentByStudent_no(student_no);
		
		close(con);
		return studentList;
	}

	public ArrayList<Student> getSearchStudentListByStudent_year(int student_year) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		
		ArrayList<Student> studentList = studentDAO.selectStudentListByStudent_year(student_year);
		close(con);
		return studentList;
	}
	
}
