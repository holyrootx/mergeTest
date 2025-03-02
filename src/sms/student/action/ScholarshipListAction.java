package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.svc.ScholarshipListService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Scholarship;

public class ScholarshipListAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipListService scholarshipListService = new ScholarshipListService();

	@Override
	public void execute(Scanner sc) throws Exception {
		ArrayList<Scholarship> scholarshipList = new ArrayList<>();
		
		scholarshipList = scholarshipListService.getScholarshipList();
		
		if( scholarshipList.size() > 0 ) {
			consoleUtil.printScholarshipList(scholarshipList);			
		}
		else {
			consoleUtil.printScholarshipListNotFound();
		}
	}
	
}
