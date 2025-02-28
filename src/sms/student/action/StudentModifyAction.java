package sms.student.action;

import java.util.Scanner;
import sms.student.svc.StudentModifyService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Student;

public class StudentModifyAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	StudentModifyService studentModifyService =	new StudentModifyService();

	@Override
	public void execute(Scanner sc) throws Exception {
		int stu_no = consoleUtil.getStudent_no("", sc);
		// 학번 받아서 getModifyStudent 실행
		Student changeStudent = studentModifyService.getModifyStudent(stu_no);
 
		if(changeStudent == null) {
			// 해당하는 학생이 없다면
			consoleUtil.printStudentNotFound(stu_no);
			return;
		}
		// 있으면 그대로 진행
		// 
		Student modifiedStudent = consoleUtil.getChangeStudent(changeStudent, sc);
		
		String unfitForm = studentModifyService.compareBirthRegistForm(modifiedStudent);
		if(unfitForm != null) {
			// 형식이 다른놈
			consoleUtil.printUnfitForm(unfitForm);
			return;
		}
		
		boolean isModifySuccess = studentModifyService.modifyStudent(modifiedStudent);
		if(isModifySuccess) {
			consoleUtil.printModifySuccess(stu_no);
		}else {
			consoleUtil.printModifyFail(stu_no);
		}
	}
	
}
