package sms.student.action;

import java.util.Scanner;
import sms.student.svc.StudentDeleteService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentDeleteAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentDeleteService studentDeleteService = new StudentDeleteService();

	@Override
	public void execute(Scanner sc) throws Exception {
		
		
	}
	
}
