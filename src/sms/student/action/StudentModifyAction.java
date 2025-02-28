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
		// �й� �޾Ƽ� getModifyStudent ����
		Student changeStudent = studentModifyService.getModifyStudent(stu_no);
 
		if(changeStudent == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printStudentNotFound(stu_no);
			return;
		}
		// ������ �״�� ����
		// 
		Student modifiedStudent = consoleUtil.getChangeStudent(changeStudent, sc);
		
		String unfitForm = studentModifyService.compareBirthRegistForm(modifiedStudent);
		if(unfitForm != null) {
			// ������ �ٸ���
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
