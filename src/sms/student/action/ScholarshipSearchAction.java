package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.controller.StudentController;
import sms.student.svc.ScholarshipSearchService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Scholarship;

public class ScholarshipSearchAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipSearchService scholarshipSearchService = new ScholarshipSearchService();

	@Override
	public void execute(Scanner sc) throws Exception {
		// getStudentList()
		ArrayList<Scholarship> searchScholarshipList = new ArrayList<>();
		int selectService = consoleUtil.getSearchMenuNumScholarship(sc);
		// 1. 장학금명
		// 2. 장학금액
		
				
		if(selectService == 1) {
			// 이름
			String scholar_name = consoleUtil.getScholar_name("조회 할 ", sc);
			
			searchScholarshipList = scholarshipSearchService.getSearchScholarshipListBySc_name(scholar_name);
			
			if(searchScholarshipList.size() == 0) {
				consoleUtil.printScholarshipListNotFound();
				
			} else {
				consoleUtil.printScholarshipList(searchScholarshipList);
			}
			
		} else if(selectService == 2) {
			//학번
			int scholar_money = consoleUtil.getMoney("조회 할 장학금 ", sc);
			
			searchScholarshipList = scholarshipSearchService.getSearchScholarshipListByMoney(scholar_money);
			
			if(searchScholarshipList.size() == 0) {
				consoleUtil.printScholarshipListNotFound();
			} else {
				consoleUtil.printScholarshipList(searchScholarshipList);
			}
			
		} else {
			consoleUtil.printSearchMenuNumWrong();
			
			Action action = new ScholarshipSearchAction();
			
			StudentController studentController = new StudentController();
			studentController.requestProcess(action, sc);
			
			return;
		}
						 
		
			
			
	}
	
}
