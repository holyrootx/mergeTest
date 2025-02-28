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
		String unfitForm = null;
	    String birth = newStudent.getStudent_birth();
	    // 자리수 확인
	    if(birth.length() != 10 || birth.indexOf("-") != 4 || birth.indexOf("-", 5) != 7) {
	       return birth;
	    }

	    String birthYear = birth.substring(0, 4);
	    String birthMonth = birth.substring(5, 7);
	    String birthDay = birth.substring(8);
	      
	    if(Integer.parseInt(birthYear) > Calendar.getInstance().get(Calendar.YEAR)
	       || Integer.parseInt(birthMonth) > 12 || Integer.parseInt(birthMonth) < 1) {
	       return birth;
	    }
	      
	    int day = 0;
	      
	    switch (birthMonth) {
	    case "01": case "03": case "05": case "07": case "08": case "10": case "12":
	       day = 31;
	       break;
	    case "04": case "06": case "09": case "11":
	       day = 30;
	       break;
	    case "02":
	       day = 28;
	       if(Integer.parseInt(birthYear)%4==0 && Integer.parseInt(birthYear)%100!=0
	          || Integer.parseInt(birthYear)%400==0) {
	          day = 29;   
	       }
	       break;
	    default:
	       return birth;
	    }
	      
	    if (Integer.parseInt(birthDay) > day || Integer.parseInt(birthDay) < 1) {
	       return birth;
	    }
	    
	    return unfitForm;
	}
	
	public boolean searchStudent(int student_no) throws Exception{
		boolean isRegisted = false;
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con); // dao 매번 새로 만들기 때문에 con 객체도 새로 생성됨
		
		Student student = studentDAO.selectStudent(student_no);
		
		if(student != null) {
			// 학생이 없다면 등록이 안되었다는 의미 
			// null이 아니면 객체가 있다는 말.
			// 여기는 학생이 있으면 isRegisted를 true로 바꾼다.
			isRegisted = true;
			return isRegisted;
		}
		// 등록을 진행한다.
		
		return false;
	}
	
	public boolean registStudent(Student newStudent) throws Exception{
		boolean isRegistSuccess = false;
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		// 문자열 -> DATE 타입으로 캐스팅하는 방법
		Date birth = null;
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long time = sdf.parse(newStudent.getStudent_birth()).getTime();
			birth = new Date(time);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int insertCount = studentDAO.insertStudent(newStudent, birth);
		if (insertCount > 0) {
			isRegistSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return isRegistSuccess;
	}
	
}
