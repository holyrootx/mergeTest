package sms.student.action;

import java.util.Scanner;
import sms.student.svc.ScholarshipDeleteService;
import sms.student.util.ConsoleUtil;
import sms.student.vo.Grade;
import sms.student.vo.Scholarship;

public class ScholarshipDeleteAction implements Action {
	
	ConsoleUtil consoleUtil = new ConsoleUtil();
	ScholarshipDeleteService scholarshipDeleteService = new ScholarshipDeleteService();

	@Override
	public void execute(Scanner sc) throws Exception {
		String scholarship_name = consoleUtil.getScholar_name("삭제할 ", sc);
		// �й� �޾Ƽ� getModifyStudent ����
		
		Scholarship deleteScholarship = scholarshipDeleteService.getDeleteScholarship(scholarship_name);
		
		if(deleteScholarship == null) {
			// �ش��ϴ� �л��� ���ٸ�
			consoleUtil.printScholarshipNotFound(scholarship_name);
			return;
		}
		
		boolean isDeleteSuccess = scholarshipDeleteService.deleteScholarship(scholarship_name);
		
		// ���� ���н�
		if(isDeleteSuccess) {
			consoleUtil.printDeleteSuccess(deleteScholarship);
		} else {
			consoleUtil.printDeleteFail(deleteScholarship);
		}		
		
		
	}
	
}
