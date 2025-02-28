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
		int stu_no = consoleUtil.getStudent_no("", sc);
		
		boolean isRegisted = studentRegistService.searchStudent(stu_no);
		
		if (isRegisted) {
			consoleUtil.printRegistedStudent(stu_no);
			// 이미 등록되어있습니다.
			return;
		} 
		// 계속 진행
		
		Student newStudent = consoleUtil.getNewStudent(stu_no, sc);
		
		String unfitForm = studentRegistService.compareBirthRegistForm(newStudent);
		// 맞으면 null
		if(unfitForm != null) {
			// 형식이 다른놈
			consoleUtil.printUnfitForm(unfitForm);
			return;
		}
		
		boolean isRegistSuccess = studentRegistService.registStudent(newStudent);
		
		if(isRegistSuccess) {
			consoleUtil.printRegistSuccess(newStudent);
		} else {
			consoleUtil.printRegistFail(newStudent);
		}
	}
	
}
