package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.ScholarshipDAO;
import sms.student.vo.Scholarship;

public class ScholarshipDeleteService {

	public Scholarship getDeleteScholarship(String sc_name) throws Exception{
		
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		Scholarship scholarship = scholarshipDAO.selectScholarship(sc_name);
		
		close(con);
		return scholarship;
	}

	public boolean deleteScholarship(String sc_name) throws Exception{
		
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		int deleteCount = scholarshipDAO.deleteScholarship(sc_name);
		boolean isDeleteSuccess = true;
		
		if(deleteCount > 0 ) {
			isDeleteSuccess = true;
			con.commit();
		}else {
			con.rollback();
		}
		

		
		close(con);
		return isDeleteSuccess;
	}
	
}
