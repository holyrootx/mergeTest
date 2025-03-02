package sms.student.action;

import java.util.Scanner;
import sms.student.svc.ScholarshipRegistService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Scholarship;

public class ScholarshipRegistAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipRegistService scholarshipRegistService = new ScholarshipRegistService();

	@Override
	public void execute(Scanner sc) throws Exception {
		String scholar_name = consoleUtil.getScholar_name("", sc);
		
		boolean isRegisted = scholarshipRegistService.searchScholarship(scholar_name);
		
		if (isRegisted) {
			consoleUtil.printRegistedScholarship(scholar_name);
			// �̹� ��ϵǾ��ֽ��ϴ�.
			return;
		} 
		// ��� ����
		
		Scholarship newScholarship = consoleUtil.getNewScholarShip(scholar_name, sc);
		
		
		boolean isRegistSuccess = scholarshipRegistService.registScholarship(newScholarship);
		
		if(isRegistSuccess) {
			consoleUtil.printRegistSuccess(newScholarship);
		} else {
			consoleUtil.printRegistFail(newScholarship);
		}		
		
	}
	
}
