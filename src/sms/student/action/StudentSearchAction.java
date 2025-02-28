package sms.student.action;

import static sms.db.JdbcUtil.close;
import static sms.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import sms.student.controller.StudentController;
import sms.student.dao.StudentDAO;
import sms.student.svc.StudentSearchService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentSearchAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentSearchService studentSearchService =	new StudentSearchService();

	@Override
	public void execute(Scanner sc) throws Exception {
		// getStudentList()
		ArrayList<Student> searchStudentList = new ArrayList<>();
		int selectService = consoleUtil.getSearchMenuNum(sc);
		
		// 1. 이름으로
		// 2. 학번으로
		// 3. 학년으로
		// 4 검색 취소
		if(selectService == 1) {
			String student_name = consoleUtil.getStudent_name("검색할", sc);
			searchStudentList = studentSearchService.getSearchStudentListByStudent_name(student_name);
			if(searchStudentList == null) {
				consoleUtil.printSearchStudentListNotFound();
			} else {
				consoleUtil.printStudentList(searchStudentList);
			}
			
		} else if(selectService == 2) {
			int student_no = consoleUtil.getStudent_no("검색할",sc);
			searchStudentList = studentSearchService.getSearchStudentListByStudent_no(student_no);
			if(searchStudentList == null) {
				consoleUtil.printSearchStudentListNotFound();
			} else {
				consoleUtil.printStudentList(searchStudentList);
			}
			
		} else if(selectService == 3) {
			int student_year = consoleUtil.getGrade("검색할",sc);
			searchStudentList = studentSearchService.getSearchStudentListByStudent_year(student_year);
			if(searchStudentList == null) {
				consoleUtil.printSearchStudentListNotFound();
			} else {
				consoleUtil.printStudentList(searchStudentList);
			}
			
		} else if(selectService == 4){
			consoleUtil.printSearchStudentCancel();
			return;
		} else {
			consoleUtil.printSearchMenuNumWrong();
			Action action = new StudentSearchAction();
			StudentController studentController = new StudentController();
			studentController.requestProcess(action, sc);
			return;
		}
		
		
		
	}
}
