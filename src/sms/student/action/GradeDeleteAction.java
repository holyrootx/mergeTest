package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeDeleteService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;

public class GradeDeleteAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	GradeDeleteService gradeDeleteService = new GradeDeleteService();

	@Override
	public void execute(Scanner sc) throws Exception {
		int student_no = consoleUtil.getStudent_no("삭제할 학생의 ", sc);
		// �й� �޾Ƽ� getModifyStudent ����
		Grade deleteGrade = gradeDeleteService.getDeleteScore(student_no);
		
		if(deleteGrade == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printStudentNotFound(student_no);
			return;
		}
		
		boolean isDeleteSuccess = gradeDeleteService.deleteGrade(student_no);
		
		// ���� ���н�
		if(isDeleteSuccess) {
			consoleUtil.printDeleteSuccess(deleteGrade);
		} else {
			consoleUtil.printDeleteFail(deleteGrade);
		}		
		
		
	}
	
}
