package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.svc.GradeListService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;

public class GradeListAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	GradeListService gradeListService = new GradeListService();
	
	@Override
	public void execute(Scanner sc) throws Exception {
		// ��ü ��ȸ
		ArrayList<Grade> gradeList = gradeListService.getGradeList();
		
		consoleUtil.printGradeList(gradeList);
		
		
		
	}
	
}
