package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.svc.StudentListService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentListAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentListService studentListService =	new StudentListService();

	@Override
	public void execute(Scanner sc) throws Exception {
		
		ArrayList<Student> studentList = studentListService.getStudentList();
		
		if(studentList != null) {
			//조회 성공했으면
			consoleUtil.printStudentList(studentList);
		} else {
			consoleUtil.printStudentListNotFound();
		}
	}
	
}
