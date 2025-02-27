package sms.student.action;

import java.util.Scanner;
import sms.student.svc.StudentRegistService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentRegistAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentRegistService studentRegistService = new StudentRegistService();
	
	@Override
	public void execute(Scanner sc) throws Exception{

		
	}
	
}
