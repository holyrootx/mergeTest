package sms.student.svc;

import static sms.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import sms.student.dao.StudentDAO;
import sms.student.vo.Student;

public class StudentModifyService {

	public Student getModifyStudent(int student_no) throws Exception{
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con);
		Student student = studentDAO.selectStudent(student_no);
		// �й� ������ ���� ��� �л� ��������
		
		return student;
	}
	
	public String compareBirthRegistForm(Student changeStudent) {
		String unfitForm = null;
	    String birth = changeStudent.getStudent_birth();
	    // �ڸ��� Ȯ��
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

	public boolean modifyStudent(Student changeStudent) throws Exception{
		Date birth = null;
		Connection con = getConnection();
		StudentDAO studentDAO = new StudentDAO(con); // dao �Ź� ���� ����� ������ con ��ü�� ���� ������
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long time = sdf.parse(changeStudent.getStudent_birth()).getTime();
			birth = new Date(time);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// ���� ���� ���� ���� �޴� ���� ¥����
		// isModifySuccess ���� ����
		boolean isModifySuccess = false;
		int updateCount = studentDAO.updateStudent(changeStudent, birth);
		// executeQuery() �����ϸ� 0���� ū ���� �Ѱ��ִϱ� 0���� ��
		if (updateCount > 0) {
			// ������ �������� ��ȯ�ϴ� ���� �� ����
			isModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		con.close();
		return isModifySuccess;
	}
}
