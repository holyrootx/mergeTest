package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.controller.StudentController;
import sms.student.svc.GradeSearchService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;

public class GradeSearchAction implements Action {
    ConsoleUtil consoleUtil = new ConsoleUtil();
    GradeSearchService gradeSearchService = new GradeSearchService();

    @Override
    public void execute(Scanner sc) throws Exception {
        int searchMenuNum = consoleUtil.getSearchMenuNum(sc);
        ArrayList<Grade> searchGradeList = null;

        if (searchMenuNum == 1) {
            String stu_name = consoleUtil.getStudent_name("검색할 ", sc);
            searchGradeList = gradeSearchService.getSearchGradeListByStudent_name(stu_name);
        } else if (searchMenuNum == 2) {
            int stu_no = consoleUtil.getStudent_no("검색할 ", sc);
            searchGradeList = gradeSearchService.getSearchGradeListByStudent_no(stu_no);

            if (searchGradeList.isEmpty()) {
                consoleUtil.printGradeNotFound(stu_no);
                return;
            }
        } else if (searchMenuNum == 3) {
            int student_year = consoleUtil.getGrade("검색할 ", sc);
            searchGradeList = gradeSearchService.getSearchGradeListByStudent_year(student_year);
        } else if (searchMenuNum == 4) {
            consoleUtil.printSearchGradeCancel();
            return;
        } else {
            consoleUtil.printSearchMenuNumWrong();
            return;
        }

        if (searchGradeList != null && !searchGradeList.isEmpty()) {
            consoleUtil.printGradeList(searchGradeList);
        } else {
            consoleUtil.printSearchGradeListNotFound();
        }
    }
}
