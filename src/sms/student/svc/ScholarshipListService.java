package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import sms.student.dao.ScholarshipDAO;
import sms.student.vo.Scholarship;

public class ScholarshipListService {

	public ArrayList<Scholarship> getScholarshipList() throws Exception{
		Connection con = getConnection();
		
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		ArrayList<Scholarship> scholarshipList = scholarshipDAO.selectScholarshipList();
		
		close(con);
		return scholarshipList;
	}
	
}
