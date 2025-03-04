package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.GradeDAO;
import sms.student.dao.StudentDAO;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeRegistService {
    public Student getRightStudent(int student_no) throws Exception {
        Connection con = getConnection();
        StudentDAO studentDAO = new StudentDAO(con);
        Student student = studentDAO.selectStudent(student_no);
        close(con);
        return student;
    }

    public boolean searchGrade(int student_no) throws Exception {
        Connection con = getConnection();
        GradeDAO gradeDAO = new GradeDAO(con);
        boolean isRegisted = gradeDAO.selectGrade(student_no) != null;
        close(con);
        return isRegisted;
    }

    public boolean registGrade(Grade newGrade) throws Exception {
        Connection con = getConnection();
        GradeDAO gradeDAO = new GradeDAO(con);
        
        int insertCount = gradeDAO.insertGrade(newGrade);
        
        if (insertCount > 0) {
            commit(con);
            close(con);
            return true;
        } else {
            rollback(con);
            return false;
        }
    }
}