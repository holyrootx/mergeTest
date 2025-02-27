package sms.student.dao;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sms.student.vo.Scholarship;

public class ScholarshipDAO {

	Connection con;

	public ScholarshipDAO(Connection con) {
		this.con = con;
	}

	public Scholarship selectScholarship(String scholar_name) throws Exception{

		
		return null;
	}

	public int insertScholarship(Scholarship newScholarship) throws Exception{

		
		return 0;
	}

	public ArrayList<Scholarship> selectScholarshipList() throws Exception{

		
		return null;
	}

	public ArrayList<Scholarship> selectScholarshipByScholar_name(String scholar_name) throws Exception{

		
		return null;
	}

	public ArrayList<Scholarship> selectScholarshipByScholar_Money(int scholar_money) throws Exception{

		
		return null;
	}

	public int updateScholarship(Scholarship changeScholarship) throws Exception{

		
		return 0;
	}

	public int deleteScholarship(String scholar_name) throws Exception{

		
		return 0;
	}
	
}
