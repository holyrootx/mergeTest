package sms.student.dao;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import sms.student.vo.Student;

public class StudentDAO {
	
	Connection con;
	
	public StudentDAO(Connection con) {
		this.con = con;
	}

	public int insertStudent(Student newStudent, Date birth) throws Exception{

		
		return 0;
	}

	public ArrayList<Student> selectStudentList() throws Exception{

		
		return null;
	}

	public Student selectStudent(int student_no) throws Exception{

		
		return null;
	}

	public ArrayList<Student> selectStudentByStudent_no(int student_no) {

		
		return null;
	}

	public ArrayList<Student> selectStudentListByStudent_name(String student_name) throws Exception{

		
		return null;
	}

	public ArrayList<Student> selectStudentListByStudent_year(int student_year) throws Exception {

		
		return null;
	}

	public int deleteStudent(int student_no) throws Exception{

		
		return 0;
	}


	public int updateStudent(Student changeStudent, Date birth) throws Exception{

		
		return 0;
	}
	
}
