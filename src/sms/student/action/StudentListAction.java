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
		
		
	}
	
}
