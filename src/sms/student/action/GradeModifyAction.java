package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeModifyService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeModifyAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	GradeModifyService gradeModifyService =	new GradeModifyService();

	@Override
	public void execute(Scanner sc) throws Exception {	
		
		int stu_no = consoleUtil.getStudent_no("¼öÁ¤ ", sc);
		Grade modifyGrade = gradeModifyService.getModifyGrade(stu_no);
		
		if (modifyGrade == null) {
			consoleUtil.printStudentNotFound(stu_no);
			return;
		}
		
		Grade changeGrade = consoleUtil.getChangeGrade(modifyGrade, sc);
		boolean isModifySuccess = gradeModifyService.modifyGrade(changeGrade);
		
		if (isModifySuccess) {
			consoleUtil.printModifySuccess(stu_no);
		} else {
			consoleUtil.printModifyFail(stu_no);
		} 
	}
		
		

}

