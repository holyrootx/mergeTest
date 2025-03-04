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
    public int insertGrade(Grade newGrade) throws Exception {
        PreparedStatement pstmt = null;
        int insertCount = 0;

        
        String sql = "INSERT INTO grade (student_no, grade_kor, grade_eng, grade_math) VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, newGrade.getStudent_no());
            pstmt.setInt(2, newGrade.getGrade_kor());
            pstmt.setInt(3, newGrade.getGrade_eng());
            pstmt.setInt(4, newGrade.getGrade_math());

            insertCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return insertCount;
    }

	public ArrayList<Grade> selectGradeList() throws Exception {
	    ArrayList<Grade> gradeList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    
	    String sql = "SELECT g.student_no, s.student_name, g.grade_kor, g.grade_eng, g.grade_math " +
	                 "FROM grade g " +
	                 "JOIN student s ON g.student_no = s.student_no";

	    try {
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int studentNo = rs.getInt("student_no");
	            String studentName = rs.getString("student_name");
	            int gradeKor = rs.getInt("grade_kor");
	            int gradeEng = rs.getInt("grade_eng");
	            int gradeMath = rs.getInt("grade_math");

	            
	            int total = gradeKor + gradeEng + gradeMath;
	            float avg = (float) total / 3;

	            
	            Grade grade = new Grade(studentNo, studentName, gradeKor, gradeEng, gradeMath);
	            grade.setTotal(total);
	            grade.setAvg(avg);
	            
	            gradeList.add(grade);
	        }
	    } finally {
	        close(rs);
	        close(pstmt);
	    }

	    return gradeList;
	}

	public ArrayList<Grade> selectGradeListByStudent_name(String student_name) throws Exception {
	    ArrayList<Grade> searchGradeList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "SELECT g.student_no, s.student_name, g.grade_kor, g.grade_eng, g.grade_math " +
	                 "FROM grade g " +
	                 "JOIN student s ON g.student_no = s.student_no " +
	                 "WHERE s.student_name LIKE '%' || ? || '%'";

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + student_name + "%"); 
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Grade grade = new Grade(
	                rs.getInt("student_no"),
	                rs.getString("student_name"),
	                rs.getInt("grade_kor"),
	                rs.getInt("grade_eng"),
	                rs.getInt("grade_math")
	            );
	            searchGradeList.add(grade);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(rs);
	        close(pstmt);
	    }

	    return searchGradeList;
	}



	public ArrayList<Grade> selectGradeListByStudent_no(int student_no) throws Exception {
		 PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    ArrayList<Grade> gradeList = new ArrayList<>();

		    String sql = "select g.student_no, s.student_name, g.grade_kor, g.grade_eng, g.grade_math " +
		                 "from grade g " +
		                 "join student s on g.student_no = s.student_no " + 
		                 "WHERE TO_CHAR(s.student_no) like ?";

		    try {
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, "%" + student_no + "%"); 
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            gradeList.add(new Grade(
		                rs.getInt("student_no"),
		                rs.getString("student_name"), 
		                rs.getInt("grade_kor"),
		                rs.getInt("grade_eng"),
		                rs.getInt("grade_math")
		            ));
		        }
		    } finally {
		        close(rs);
		        close(pstmt);
		    }
		    return gradeList;
	}


	public ArrayList<Grade> selectGradeListByStudent_year(int student_year) throws Exception {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<Grade> gradeList = new ArrayList<>();

	    String sql = "SELECT g.student_no, s.student_name, g.grade_kor, g.grade_eng, g.grade_math " +
	                 "FROM grade g " +
	                 "JOIN student s ON g.student_no = s.student_no " +
	                 "WHERE s.student_year = ?";

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, student_year);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            gradeList.add(new Grade(
	                rs.getInt("student_no"),
	                rs.getString("student_name"),
	                rs.getInt("grade_kor"),
	                rs.getInt("grade_eng"),
	                rs.getInt("grade_math")
	            ));
	        }
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
		String sql = "SELECT student_no, grade_kor, grade_eng, grade_math FROM grade WHERE student_no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				grade = new Grade(
					rs.getInt("student_no"),
					rs.getInt("grade_kor"),
					rs.getInt("grade_eng"),
					rs.getInt("grade_math")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
		}
		return grade;
		
	}
	
	public int updateGrade(Grade changeGrade) throws Exception{

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE grade SET grade_kor = ?, grade_eng = ?, grade_math = ? WHERE student_no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, changeGrade.getGrade_kor());
			pstmt.setInt(2, changeGrade.getGrade_eng());
			pstmt.setInt(3, changeGrade.getGrade_math());
			pstmt.setInt(4, changeGrade.getStudent_no());
			
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) pstmt.close();
		}
		return updateCount;
		
	}

	public int deleteGrade(int student_no) throws Exception {
	    int deleteCount = 0; 
	    PreparedStatement pstmt = null;
	    String sql = "DELETE FROM grade WHERE student_no = ?"; 

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, student_no);
	        deleteCount = pstmt.executeUpdate();
	    } finally {
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
