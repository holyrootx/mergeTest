package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeModifyService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;

public class GradeModifyAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	GradeModifyService gradeModifyService =	new GradeModifyService();

	@Override
	public void execute(Scanner sc) throws Exception {		
		int student_no = consoleUtil.getStudent_no("성적을 수정할 ", sc);
		// �й� �޾Ƽ� getModifyStudent ����
		Grade newGrade = gradeModifyService.getModifyGrade(student_no);
 
		if(newGrade == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printStudentNotFound(student_no);
			return;
		}
		// ������ �״�� ����
		// 
		newGrade = consoleUtil.getChangeGrade(newGrade,sc);
		boolean isModifySuccess = gradeModifyService.modifyGrade(newGrade);
		
		if(isModifySuccess) {
			consoleUtil.printModifySuccess(newGrade);
		}else {
			consoleUtil.printModifyFail(newGrade);
		}	
		
	}
	
}
