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
		// getStudentList()
		ArrayList<Grade> searchGradeList = new ArrayList<>();
		int selectService = consoleUtil.getSearchGradeMenuNum(sc);
				
		if(selectService == 1) {
			// 이름
			String student_name = consoleUtil.getStudent_name("조회할 ", sc);
			
			searchGradeList = gradeSearchService.getSearchGradeListByStudent_name(student_name);
			
			if(searchGradeList.size() == 0) {
				consoleUtil.printSearchGradeListNotFound();
				
			} else {
				consoleUtil.printGradeList(searchGradeList);
			}
			
		} else if(selectService == 2) {
			//학번
			int student_no = consoleUtil.getStudent_no("조회할 ",sc);
			
			searchGradeList = gradeSearchService.getSearchGradeListByStudent_no(student_no);
			
			if(searchGradeList.size() == 0) {
				consoleUtil.printSearchGradeListNotFound();
			} else {
				consoleUtil.printGradeList(searchGradeList);
			}
			
		} else if(selectService == 3) {
			//학년
			int student_year = consoleUtil.getGrade("조회할 ",sc);
			searchGradeList = gradeSearchService.getSearchGradeListByStudent_year(student_year);
			if(searchGradeList.size() == 0) {
				consoleUtil.printSearchGradeListNotFound();
			} else {
				consoleUtil.printGradeList(searchGradeList);
			}
			
		} else if(selectService == 4){
			consoleUtil.printSearchGradeCancel();
			return;
		} else {
			consoleUtil.printSearchMenuNumWrong();
			Action action = new GradeSearchAction();
			StudentController studentController = new StudentController();
			studentController.requestProcess(action, sc);
			return;
		}
						 
		
		
		
	}
	
}
