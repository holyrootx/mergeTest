package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;

import sms.student.dao.GradeDAO;
import sms.student.dao.ScholarshipDAO;
import sms.student.vo.Grade;
import sms.student.vo.Scholarship;

public class ScholarshipModifyService {

	public Scholarship getModifyScholarship(String sc_name) throws Exception{
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		Scholarship newScholarship = scholarshipDAO.selectScholarship(sc_name);
		
		con.close();
		
		return newScholarship;
	}

	public boolean modifyScholarship(Scholarship changeScholarship) throws Exception{
		Connection con = getConnection();
		ScholarshipDAO scholarshipDAO = new ScholarshipDAO(con);
		
		
		int updateCount = scholarshipDAO.updateScholarship(changeScholarship);
		
		boolean isModifySuccess = false;
		
		if(updateCount > 0) {
			isModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		con.close();
		
		return isModifySuccess;
	}
	
}
