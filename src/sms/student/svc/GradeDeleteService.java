package sms.student.svc;

import static sms.db.JdbcUtil.*;
import java.sql.Connection;
import sms.student.dao.GradeDAO;
import sms.student.vo.Grade;

public class GradeDeleteService {

    // 성적 정보 조회
    public Grade getDeleteScore(int student_no) throws Exception {
    	
        Connection con = getConnection();
        GradeDAO gradeDAO = new GradeDAO(con);
        Grade deleteGrade = gradeDAO.selectGrade(student_no);
        close(con);
        
        return deleteGrade;
    }


    public boolean deleteGrade(int student_no) throws Exception {
    	
    	boolean isDeleteSuccess = false;
        Connection con = getConnection();
        GradeDAO gradeDAO = new GradeDAO(con);
        int deleteCount = gradeDAO.deleteGrade(student_no);
        
        if (deleteCount > 0) {
        	isDeleteSuccess = true;
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
        return isDeleteSuccess;
    }
}