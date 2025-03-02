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
		int stu_no = consoleUtil.getStudent_no("", sc);
		// �й� �޾Ƽ� getModifyStudent ����
		Student deleteStudent = studentDeleteService.getDeleteStudent(stu_no);
		
		if(deleteStudent == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printStudentNotFound(stu_no);
			return;
		}
		
		boolean isDeleteSuccess = studentDeleteService.deleteStudent(stu_no);
		
		// ���� ���н�
		if(isDeleteSuccess) {
			consoleUtil.printDeleteSuccess(stu_no);
		} else {
			consoleUtil.printDeleteFail(stu_no);
		}
		
		
	}
	
}
