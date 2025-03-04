package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeRegistService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeRegistAction implements Action {
    ConsoleUtil consoleUtil = new ConsoleUtil();
    GradeRegistService gradeRegistService = new GradeRegistService();

    @Override
    public void execute(Scanner sc) throws Exception {
        int stu_no = consoleUtil.getStudent_no("", sc);

        boolean isRegisted = gradeRegistService.searchGrade(stu_no);
        if (isRegisted) {
            consoleUtil.printRegistedGrade(stu_no);
            return;
        }

        
        Student student = gradeRegistService.getRightStudent(stu_no);
        if (student == null) {
            consoleUtil.printStudentNotFound(stu_no); 
            return;
        }

        Grade newGrade = consoleUtil.getNewGrade(stu_no, sc);
        boolean isRegistGrade = gradeRegistService.registGrade(newGrade);

        if (isRegistGrade) {
            consoleUtil.printRegistSuccess(newGrade);
        } else {
            consoleUtil.printRegistFail(newGrade);
        }
    }
}
