package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import sms.student.dao.StudentDAO;
import sms.student.vo.Student;
import static sms.db.JdbcUtil.*;

public class StudentRegistService {

	public String compareBirthRegistForm(Student newStudent) {

		
		return null;
	}
	
	public boolean searchStudent(int student_no) throws Exception{
		boolean isRegisted = false;
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		Student student = studentDAO.selectStudent(student_no);
		
		if(studentDAO != null) {
			isRegisted = true;
		}
		return false;
	}
	
	public boolean registStudent(Student newStudent) throws Exception{

		
		
		return false;
	}
	
}
