package sms.student.dao;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sms.student.vo.Grade;

public class GradeDAO {
	
	Connection con;

	public GradeDAO(Connection con) {
		this.con = con;
	}

	public int insertGrade(Grade newGrade) throws Exception{
		
		
		return 0;
	}

	public ArrayList<Grade> selectGradeList() throws Exception{

		
		return null;
	}

	public ArrayList<Grade> selectGradeListByStudent_name(String student_name) throws Exception {

		
		return null;
	}

	public ArrayList<Grade> selectGradeListByStudent_no(int student_no) throws Exception{

		
		return null;
	}

	public ArrayList<Grade> selectGradeListByStudent_year(int student_year) throws Exception{

		
		return null;
	}
	
	public Grade selectGrade(int student_no) throws Exception{

		
		return null;
	}
	
	public int updateGrade(Grade changeGrade) throws Exception{

		
		return 0;
	}

	public int deleteGrade(int student_no) throws Exception {

		
		return 0;
	}

	public ArrayList<Grade> selectGradeListAddPercent(int gradeListSize) throws Exception{
		
		
		return null;
	}
	
}
