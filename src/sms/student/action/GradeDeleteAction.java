package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeDeleteService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;

public class GradeDeleteAction implements Action {

    ConsoleUtil consoleUtil = new ConsoleUtil();
    GradeDeleteService gradeDeleteService = new GradeDeleteService();

    @Override
    public void execute(Scanner sc) throws Exception {
    	
        int stu_no = consoleUtil.getStudent_no("ªË¡¶«“ ", sc);
        Grade deleteGrade = gradeDeleteService.getDeleteScore(stu_no);
        
        if (deleteGrade == null) {
            consoleUtil.printStudentNotFound(stu_no);
            return;
        }

        
        boolean isDeleteSuccess = gradeDeleteService.deleteGrade(stu_no);

        if (isDeleteSuccess) {
            consoleUtil.printDeleteSuccess(deleteGrade);
        } else {
            consoleUtil.printDeleteFail(deleteGrade);
        }
    }
}
