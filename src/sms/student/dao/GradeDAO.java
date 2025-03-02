package sms.student.dao;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeDAO {
	
	Connection con;

	public GradeDAO(Connection con) {
		this.con = con;
	}

	public int insertGrade(Grade newGrade) throws Exception{
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO grade "
				+ " VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newGrade.getStudent_no());
			pstmt.setInt(2, newGrade.getGrade_kor());
			pstmt.setInt(3, newGrade.getGrade_eng());
			pstmt.setInt(4, newGrade.getGrade_math());
			insertCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public ArrayList<Grade> selectGradeList() throws Exception{
		ArrayList<Grade> gradeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Grade grade = null;
		String sql = "SELECT std.student_no, student_name, grade_kor,grade_eng,grade_math"
				+ " FROM student std "
				+ " INNER JOIN grade gr "
				+ " ON std.student_no = gr.student_no";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math")
						);
				gradeList.add(grade);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return gradeList;

	}

	public ArrayList<Grade> selectGradeListByStudent_name(String student_name) throws Exception {
		
		
		ArrayList<Grade> gradeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Grade grade = null;
		String sql =" SELECT g.student_no,student_name,student_year,grade_kor,grade_eng, grade_math"
				+ " FROM grade g "
				+ " JOIN student s "
				+ " ON g.student_no = s.student_no "
				+ " WHERE student_name LIKE '%' || ? || '%'"; 
				
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math")
						);
				gradeList.add(grade);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);			
			}
		return gradeList;			

	}

	public ArrayList<Grade> selectGradeListByStudent_no(int student_no) throws Exception{
	
		
		ArrayList<Grade> gradeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT g.student_no,student_name,student_year,grade_kor,grade_eng,grade_math"
				+ " FROM grade g "
				+ " JOIN student s "
				+ " ON g.student_no = s.student_no"
				+ " WHERE g.student_no = ?";
		Grade grade = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// GRADE 순서
				// 
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math")
						);
				gradeList.add(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(rs);
			close(pstmt);
		}
		return gradeList;				
	}

	public ArrayList<Grade> selectGradeListByStudent_year(int student_year) throws Exception{
		
		ArrayList<Grade> gradeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT g.student_no,student_name,student_year,grade_kor,grade_eng,grade_math"
				+ " FROM grade g "
				+ " JOIN student s "
				+ " ON g.student_no = s.student_no"
				+ " WHERE student_year = ?";
		Grade grade = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_year);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// GRADE 순서
				// 
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math")
						);
				gradeList.add(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(rs);
			close(pstmt);
		}
		return gradeList;	
	}
	
	public Grade selectGrade(int student_no) throws Exception{
		
		Grade grade = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM grade "
				+ " WHERE student_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math")
						);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return grade;
	}
	
	public int updateGrade(Grade changeGrade) throws Exception{
		System.out.println("updateGrade는 실행됨");
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE grade"
				   + " SET grade_kor = ?, grade_eng = ?, grade_math = ?"
				   + " WHERE student_no = ?";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, changeGrade.getGrade_kor());
			pstmt.setInt(2, changeGrade.getGrade_eng());
			pstmt.setInt(3, changeGrade.getGrade_math());
			pstmt.setInt(4, changeGrade.getStudent_no());
			updateCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
			
		return updateCount;
	}

	public int deleteGrade(int student_no) throws Exception {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		// DELETE FROM ���̺��
		// WHERE [���ǽ�]
		String sql = "DELETE FROM grade"
				+ " WHERE student_no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			deleteCount = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		
		return deleteCount;
		
	}

	public ArrayList<Grade> selectGradeListAddPercent() throws Exception{
		
		ArrayList<Grade> gradeListAddPercent = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT g.student_no,student_name,grade_kor,grade_eng, grade_math,"
				+ " (grade_kor+grade_eng+grade_math) / 3 AS avg ,"
				+ " RANK() OVER (ORDER BY (grade_kor+grade_eng+grade_math) DESC) / (SELECT COUNT(*) FROM grade) AS grade_percent"
				+ " FROM grade g "
				+ " JOIN student s "
				+ " ON g.student_no = s.student_no";
		Grade grade = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// GRADE 순서
				// 
				grade = new Grade(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("grade_kor"),
						rs.getInt("grade_eng"),
						rs.getInt("grade_math"),
						rs.getFloat("grade_percent")
						);
				
				gradeListAddPercent.add(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(rs);
			close(pstmt);
		}
		return gradeListAddPercent;			

	}
	
}
