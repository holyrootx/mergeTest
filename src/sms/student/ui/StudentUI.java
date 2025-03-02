package sms.student.ui;

import java.util.Scanner;
import sms.student.action.Action;
import sms.student.action.ScholarshipDeleteAction;
import sms.student.action.ScholarshipListAction;
import sms.student.action.ScholarshipModifyAction;
import sms.student.action.ScholarshipRegistAction;
import sms.student.action.ScholarshipSearchAction;
import sms.student.action.ScholarshipStudentSearchAction;
import sms.student.action.GradeDeleteAction;
import sms.student.action.GradeListAction;
import sms.student.action.GradeModifyAction;
import sms.student.action.GradeRegistAction;
import sms.student.action.GradeSearchAction;
import sms.student.action.StudentDeleteAction;
import sms.student.action.StudentListAction;
import sms.student.action.StudentModifyAction;
import sms.student.action.StudentRegistAction;
import sms.student.action.StudentSearchAction;
import sms.student.controller.StudentController;

public class StudentUI {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean isOuterStop = false;
		boolean isInnerStop = false;
		int outerMenu = 0;
		int innerMenu = 0;
		
		Action action = null;
		StudentController studentController = new StudentController();
		
		do{
			System.out.println("\n───────< 학생정보관리 프로그램 >──┬────");
			System.out.println("             1. 학생정보 관리              │ ");
			System.out.println("             2. 성적정보 관리             ◀△ ");
			System.out.println("             3. 장학금 관리               ▽▶");
			System.out.println("             4. 프로그램 종료                 ");
			System.out.print("             선택 : ");
			outerMenu = sc.nextInt();
			
			isInnerStop = false;

			switch (outerMenu) {
			
			case 1:
				do{
					System.out.println("\n─────────< 학생정보 관리 >────┬────");
					System.out.println("             1. 학생정보 입력              │");
					System.out.println("             2. 학생정보 조회             △▶");
					System.out.println("             3. 학생정보 검색             ◀▽");
					System.out.println("             4. 학생정보 변경                 ");
					System.out.println("             5. 학생정보 삭제                 ");
					System.out.println("             6. 첫 화면으로                   ");
					System.out.print("             선택 : ");
					innerMenu = sc.nextInt();
					
					switch (innerMenu) {
					
					case 1:
						action = new StudentRegistAction();
						break;
						
					case 2:
						action = new StudentListAction();
						break;
						
					case 3:
						action = new StudentSearchAction();
						break;
						
					case 4:
						action = new StudentModifyAction();
						break;
						
					case 5:
						action = new StudentDeleteAction();
						break;
						
					case 6:
						System.out.println("             첫 화면으로 이동합니다.\n");
						isInnerStop = true;
						action = null;
						break;
						
					default:
						System.out.println("             잘못 입력하셨습니다.\n");
						action = null;
						break;
					}
					
					if(action != null) {
						studentController.requestProcess(action, sc);
					}
				}while(!isInnerStop);
				break;
				
			case 2:
				do{
					System.out.println("\n─────────< 성적정보 관리 >────┬────");
					System.out.println("             1. 성적정보 입력              │");
					System.out.println("             2. 성적정보 조회             △▶           ");
					System.out.println("             3. 성적정보 검색             ◀▽           ");
					System.out.println("             4. 성적정보 변경                  ");
					System.out.println("             5. 성적정보 삭제                  ");
					System.out.println("             6. 첫 화면으로                    ");
					System.out.print("             선택 : ");
					innerMenu = sc.nextInt();
					
					switch (innerMenu) {
					
					case 1:
						action = new GradeRegistAction();
						break;
						
					case 2:
						action = new GradeListAction();
						break;
						
					case 3:
						action = new GradeSearchAction();
						break;
						
					case 4:
						action = new GradeModifyAction();
						break;
						
					case 5:
						action = new GradeDeleteAction();
						break;
					
					case 6:
						System.out.println("             첫 화면으로 이동합니다.\n");
						isInnerStop = true;
						action = null;
						break;
						
					default:
						System.out.println("             잘못 입력하셨습니다.\n");
						action = null;
						break;
					}
					
					if(action != null) {
						studentController.requestProcess(action, sc);
					}
				}while(!isInnerStop);
				break;
				
			case 3:
				do{
					System.out.println("\n─────────< 장학금 관리 >─────┬────");
					System.out.println("             1. 장학금종류 추가            │");
					System.out.println("             2. 장학금종류 조회           △▶");
					System.out.println("             3. 장학금종류 검색           ◀▽");
					System.out.println("             4. 장학금종류 변경               ");
					System.out.println("             5. 장학금종류 삭제               ");
					System.out.println("             6. 장학금별 수혜학생 검색        ");
					System.out.println("             7. 첫 화면으로                  ");
					System.out.print("             선택 : ");
					innerMenu = sc.nextInt();
					
					switch (innerMenu) {
					
					case 1:
						action = new ScholarshipRegistAction();
						break;
						
					case 2:
						action = new ScholarshipListAction();
						break;
						
					case 3:
						action = new ScholarshipSearchAction();
						break;
						
					case 4:
						action = new ScholarshipModifyAction();
						break;
						
					case 5:
						action = new ScholarshipDeleteAction();
						break;
						
					case 6:
						action = new ScholarshipStudentSearchAction();
						break;
					
					case 7:
						System.out.println("             첫 화면으로 이동합니다.\n");
						isInnerStop = true;
						action = null;
						break;
						
					default:
						System.out.println("             잘못 입력하셨습니다.\n");
						action = null;
						break;
					}
					
					if(action != null) {
						studentController.requestProcess(action, sc);
					}
				}while(!isInnerStop);
				break;
				
			case 4:
				System.out.println("             프로그램을 종료합니다. Bye~ 짜이찌엔~\n");
				isOuterStop = true;
				break;
				
			default:
				System.out.println("             잘못 입력하셨습니다.\n");
				break;
			}
		}while(!isOuterStop);
	}
	
}
