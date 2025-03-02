package sms.student.action;

import java.util.ArrayList;
import java.util.Scanner;

import sms.student.svc.ScholarshipStudentSearchService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Scholarship;
import sms.student.vo.ScholarshipStudent;
import sms.student.vo.Grade;

public class ScholarshipStudentSearchAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipStudentSearchService scholarshipStudentSearchService = new ScholarshipStudentSearchService();

	
	@Override
	public void execute(Scanner sc) throws Exception {
		String scholarship_name = consoleUtil.getScholar_name("학생이 받는 ", sc);
		
		Scholarship scholarship = scholarshipStudentSearchService.getSearchScholarship(scholarship_name);
		
		if (scholarship == null) {
			consoleUtil.printScholarshipNotFound(scholarship_name);
			return;
		}
		
		ArrayList<Grade> scoreListAddPercent = scholarshipStudentSearchService.getScoreListAddPercent();
		
		if(scoreListAddPercent.size()  == 0) {
			consoleUtil.printSearchGradeListNotFound();
		} else {
			consoleUtil.printGradeList(scoreListAddPercent);
		}
		
		ArrayList<ScholarshipStudent> scholarshipStudentList = scholarshipStudentSearchService.getScholarshipStudentSearchList(scholarship,scoreListAddPercent);
		
		if(scholarshipStudentList.size() == 0) {
			consoleUtil.printScholarshipStudentListNotFound();
		} else {
			consoleUtil.printScholarshipStudentList(scholarshipStudentList);
		}
		
		
	}
}
