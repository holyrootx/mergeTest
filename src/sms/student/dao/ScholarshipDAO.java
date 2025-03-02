package sms.student.dao;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sms.student.vo.Grade;
import sms.student.vo.Scholarship;

public class ScholarshipDAO {

	Connection con;

	public ScholarshipDAO(Connection con) {
		this.con = con;
	}

	public Scholarship selectScholarship(String scholar_name) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scholarship scholarship = null;
		String sql = "SELECT scholar_name, scholar_percent, scholar_money"
				+ " FROM scholarship"
				+ " WHERE scholar_name LIKE '%' || ? || '%'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, scholar_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				scholarship = new Scholarship(
							rs.getString("scholar_name"),
							rs.getInt("scholar_percent"),
							rs.getInt("scholar_money")
						);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return scholarship;
	}

	public int insertScholarship(Scholarship newScholarship) throws Exception{
		// INSERT INTO 테이블 이름 [(속성1, 속성2, 속성3, ... 속성N)
		// VALUES (속성1에 들어갈 데이터, 속성2에 들어갈 데이터, 속성3에 들어갈 데이터, ... 데이터N)속성명 오타 주의, 속성과 데이터의 타입 일치 할것
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO scholarship(scholar_name, scholar_percent, scholar_money) "
				+ " VALUES(?, ?, ?)"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newScholarship.getScholar_name());
			pstmt.setInt(2, newScholarship.getScholar_percent());
			pstmt.setInt(3, newScholarship.getScholar_money());
			
			insertCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public ArrayList<Scholarship> selectScholarshipList() throws Exception{
		ArrayList<Scholarship> scholarshipList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scholarship scholarship = null;
		String sql = "SELECT scholar_name, scholar_percent, scholar_money "
				+ " FROM scholarship";

		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				scholarship = new Scholarship(
						rs.getString("scholar_name"),
						rs.getInt("scholar_percent"),
						rs.getInt("scholar_money")
						);
				scholarshipList.add(scholarship);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return scholarshipList;		

	}

	public ArrayList<Scholarship> selectScholarshipByScholar_name(String scholar_name) throws Exception{
		ArrayList<Scholarship> scholarshipList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scholarship scholarship = null;
		String sql = "SELECT scholar_name, scholar_percent, scholar_money "
				+ " FROM scholarship "
				+ " WHERE scholar_name LIKE '%' || ? || '%'";

		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, scholar_name);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				scholarship = new Scholarship(
						rs.getString("scholar_name"),
						rs.getInt("scholar_percent"),
						rs.getInt("scholar_money")
						);
				scholarshipList.add(scholarship);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return scholarshipList;	
		
	}

	public ArrayList<Scholarship> selectScholarshipByScholar_Money(int scholar_money) throws Exception{
		
		ArrayList<Scholarship> scholarshipList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scholarship scholarship = null;
		String sql = "SELECT scholar_name, scholar_percent, scholar_money "
				+ " FROM scholarship "
				+ " WHERE scholar_money = ?";

		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scholar_money);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				scholarship = new Scholarship(
						rs.getString("scholar_name"),
						rs.getInt("scholar_percent"),
						rs.getInt("scholar_money")
						);
				scholarshipList.add(scholarship);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return scholarshipList;	

	}

	public int updateScholarship(Scholarship changeScholarship) throws Exception{

		
		return 0;
	}

	public int deleteScholarship(String scholar_name) throws Exception{

		
		return 0;
	}
	
}
