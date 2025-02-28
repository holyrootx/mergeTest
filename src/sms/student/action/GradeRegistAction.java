package sms.student.action;

import java.util.Scanner;
import sms.student.svc.GradeRegistService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class GradeRegistAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	GradeRegistService gradeRegistService = new GradeRegistService();

	@Override
	public void execute(Scanner sc) throws Exception {
		
		int student_no = consoleUtil.getStudent_no("성적을 입력할 ", sc);
		
		// 학번으로 학생을 가져온다.
		Student student = gradeRegistService.getRightStudent(student_no);
		
		// null이면 학생 정보를 못찾아온거니까 
		if (student == null) {
			consoleUtil.printStudentNotFound(student_no);
			
			return;
		} 
		// 계속 진행
		
		
		boolean isExist = gradeRegistService.searchGrade(student_no);
		// 이미 테이블에 존재한다면 이미 등록되었다는 소리니까 Cut
		if(isExist) {
			// 이미 등록되어있다는 말.
			consoleUtil.printRegistedGrade(student_no);
			return;
		}
		
		Grade newGrade = consoleUtil.getNewGrade(student_no,sc);
		
		boolean isRegistSuccess = gradeRegistService.registGrade(newGrade);
		
		if(isRegistSuccess) {
			consoleUtil.printRegistSuccess(newGrade);
		} else {
			consoleUtil.printRegistFail(newGrade);
		}
			
		
		
	}
}
