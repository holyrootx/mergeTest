package sms.student.action;

import java.util.Scanner;
import sms.student.svc.ScholarshipModifyService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Scholarship;

public class ScholarshipModifyAction implements Action {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipModifyService scholarshipModifyService = new ScholarshipModifyService();

	@Override
	public void execute(Scanner sc) throws Exception {
		String scholarship_name = consoleUtil.getScholar_name("수정할 ",sc);
		// �й� �޾Ƽ� getModifyStudent ���� getScholar_name
		Scholarship newScholarship = scholarshipModifyService.getModifyScholarship(scholarship_name);
 
		if(newScholarship == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printScholarshipListNotFound();
			return;
		}
		// ������ �״�� ����
		// 
		newScholarship = consoleUtil.getChangeScholarship(newScholarship,sc);
		
		boolean isModifySuccess = scholarshipModifyService.modifyScholarship(newScholarship);
		
		if(isModifySuccess) {
			consoleUtil.printModifySuccess(newScholarship);
		}else {
			consoleUtil.printModifyFail(newScholarship);
		}				
		
	}
	
}
