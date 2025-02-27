package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.controller.StudentController;
import sms.student.svc.StudentSearchService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentSearchAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentSearchService studentSearchService =	new StudentSearchService();

	@Override
	public void execute(Scanner sc) throws Exception {

		
	}
}
