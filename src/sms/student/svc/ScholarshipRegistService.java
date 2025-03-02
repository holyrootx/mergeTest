package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.ScholarshipDAO;
import sms.student.vo.Scholarship;

public class ScholarshipRegistService {

	public boolean searchScholarship(String sc_name) throws Exception{
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		Scholarship scholarship = scholarshipDAO.selectScholarship(sc_name);
		
		boolean isRegisted = false;
		
		if(scholarship != null) {
			// 등록된 정보가 있다면 등록되어 있다고 알림.
			isRegisted = true;
		}
		con.close();
		return isRegisted;
	}

	public boolean registScholarship(Scholarship newScholarship) throws Exception{
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		int insertCount = scholarshipDAO.insertScholarship(newScholarship);
		boolean isRegistSuccess = false;
		
		if (insertCount > 0) {
			isRegistSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		
		con.close();
		return isRegistSuccess;
	}
	
}
