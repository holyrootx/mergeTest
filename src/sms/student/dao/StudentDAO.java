package sms.student.dao;

import static sms.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sms.student.vo.Student;

public class StudentDAO {
	
	Connection con;
	
	public StudentDAO(Connection con) {
		this.con = con;
	}

	public int insertStudent(Student newStudent, Date birth) throws Exception{
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO student VALUES(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newStudent.getStudent_no());
			pstmt.setString(2,newStudent.getStudent_name());
			pstmt.setInt(3, newStudent.getStudent_year());
			pstmt.setString(4,newStudent.getStudent_addr());
			pstmt.setString(5,newStudent.getStudent_tel());
			pstmt.setDate(6, birth);
			insertCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public ArrayList<Student> selectStudentList() throws Exception{
		//전체조회
		ArrayList<Student> studentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				student = new Student(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("student_year"),
						rs.getString("student_addr"),
						rs.getString("student_tel"),
						rs.getDate("student_birth").toString()
						);
				studentList.add(student);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return studentList;
	}

	public Student selectStudent(int student_no) throws Exception{
		// 단일 학생 조회
		// 학번을 받아서 학생객체를 반환한다.
		Student student = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student WHERE student_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,student_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				student = new Student(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("student_year"),
						rs.getString("student_addr"),
						rs.getString("student_tel"),
						rs.getDate("student_birth").toString()
						);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return student;
	}

	public ArrayList<Student> selectStudentByStudent_no(int student_no) {
		//전체조회
		ArrayList<Student> studentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student WHERE student_no = ?";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				student = new Student(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("student_year"),
						rs.getString("student_addr"),
						rs.getString("student_tel"),
						rs.getDate("student_birth").toString()
						);
				studentList.add(student);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return studentList;				
	}

	public ArrayList<Student> selectStudentListByStudent_name(String student_name) throws Exception{
		//전체조회
		ArrayList<Student> searchStudentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student WHERE student_name LIKE '%' || ? || '%'";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				student = new Student(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("student_year"),
						rs.getString("student_addr"),
						rs.getString("student_tel"),
						rs.getDate("student_birth").toString()
						);
				searchStudentList.add(student);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return searchStudentList;	
	}

	public ArrayList<Student> selectStudentListByStudent_year(int student_year) throws Exception {
		//전체조회
		ArrayList<Student> searchStudentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student WHERE student_year = ?";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_year);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				student = new Student(
						rs.getInt("student_no"),
						rs.getString("student_name"),
						rs.getInt("student_year"),
						rs.getString("student_addr"),
						rs.getString("student_tel"),
						rs.getDate("student_birth").toString()
						);
				searchStudentList.add(student);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return searchStudentList;	
	}
	

	public int deleteStudent(int student_no) throws Exception{
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		// DELETE FROM 테이블명
		// WHERE [조건식]
		String sql = "DELETE FROM student"
				+ " WHERE student_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_no);
			deleteCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pstmt.close();
		}
		
		return deleteCount;
	}


	public int updateStudent(Student changeStudent, Date birth) throws Exception{
		int updateCount = 0;
		PreparedStatement pstmt = null;
		// UPDATE [변경할 TABLE명]
		// SET [변경할 열1 = 새로운 값1], [변경할 열2 = 새로운 값2]
		// 		WHERE 조건식
		String sql = "UPDATE student"
				+ " SET Student_name = ?, Student_year = ?,Student_addr = ?, Student_tel = ?, Student_birth = ? "
				+ " WHERE student_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,changeStudent.getStudent_name());
			pstmt.setInt(2, changeStudent.getStudent_year());
			pstmt.setString(3,changeStudent.getStudent_addr());
			pstmt.setString(4,changeStudent.getStudent_tel());
			pstmt.setDate(5, birth);
			pstmt.setInt(6, changeStudent.getStudent_no());
			updateCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}
	
}
