package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import sms.student.dao.StudentDAO;
import sms.student.vo.Student;

public class StudentListService {
	
	public ArrayList<Student> getStudentList() throws Exception{

		// 1. Connection
		// DAO 연결
		// dao 메서드 호출 후 결과 받아오기
		// 결과 리턴하기
		// 트랜잭션 처리 
		// 
		Connection con = getConnection();
		StudentDAO memberDAO = new StudentDAO(con);
		ArrayList<Student> studentList =  memberDAO.selectStudentList();
		
		close(con);
		return studentList;
	}
	
}