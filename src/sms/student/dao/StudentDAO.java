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
		//��ü��ȸ
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
		// ���� �л� ��ȸ
		// �й��� �޾Ƽ� �л���ü�� ��ȯ�Ѵ�.
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
		//��ü��ȸ
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
		//��ü��ȸ
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
		//��ü��ȸ
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
		PreparedStatement pstmt_child = null;
		PreparedStatement pstmt_parent = null;
		// DELETE FROM ���̺��
		// WHERE [���ǽ�]
		String sql_child = "DELETE FROM grade"
				+ " WHERE student_no = ?";
		String sql_parent = "DELETE FROM student"
				+ " WHERE student_no = ?";
		try {
			pstmt_child = con.prepareStatement(sql_child);
			pstmt_child.setInt(1, student_no);
			deleteCount = pstmt_child.executeUpdate();
			pstmt_parent = con.prepareStatement(sql_parent);
			pstmt_parent.setInt(1, student_no);
			deleteCount += pstmt_parent.executeUpdate();
			
		}catch(Exception e) {
			con.rollback();
			e.printStackTrace();
		}finally {
			pstmt_child.close();
			pstmt_parent.close();
		}
		
		return deleteCount;
	}


	public int updateStudent(Student changeStudent, Date birth) throws Exception{
		int updateCount = 0;
		PreparedStatement pstmt = null;
		// UPDATE [������ TABLE��]
		// SET [������ ��1 = ���ο� ��1], [������ ��2 = ���ο� ��2]
		// 		WHERE ���ǽ�
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
