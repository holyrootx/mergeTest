package sms.student.util;

import java.util.ArrayList;
import java.util.Scanner;
import sms.student.vo.Scholarship;
import sms.student.vo.ScholarshipStudent;
import sms.student.vo.Grade;
import sms.student.vo.Student;

public class ConsoleUtil {

	//학생 관련 콘솔 유틸

	public void printRegistedStudent(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생은 이미 등록되어 있습니다.\n");
	}

	
	public void printUnfitForm(String unfitForm) {
		System.out.println("\n> 생년월일 : " + unfitForm + "이 형식에 맞지 않습니다. (예시 : 1988-06-15)");
	}
	
	public Student getNewStudent(int student_no, Scanner sc) {
		sc.useDelimiter(System.getProperty("line.separator"));

		System.out.println("\n> 새로운 학생정보 입력");

		System.out.print("> 이름 : ");
		String stu_name = sc.next();

		System.out.print("> 학년 : ");
		int grade = sc.nextInt();

		System.out.print("> 주소 : ");
		String address = sc.next();

		System.out.print("> 전화번호 : ");
		String tel = sc.next();

		System.out.print("> 생년월일 : ");
		String birth = sc.next();

		return new Student(student_no, stu_name, grade, address, tel, birth);
	}


	public void printRegistSuccess(Student newStudent) {
		System.out.println("> 학번 : " + newStudent.getStudent_no() + " 학생의 정보가 등록되었습니다.\n");
	}


	public void printRegistFail(Student newStudent) {
		System.out.println("> 학번 : " + newStudent.getStudent_no() + " 학생의 정보 등록이 실패했습니다.\n");
	}


	public int getByteLength(String str) {
		int strLength = 0;
		char tempChar[] = new char[str.length()];

		for(int i=0 ; i<tempChar.length ; i++) {
			tempChar[i] = str.charAt(i);
			if(tempChar[i] < 128) {
				strLength++;
			}
			else {
				strLength += 2;
			}
		}
		return strLength;
	}


	public void printStudentList(ArrayList<Student> studentList) {
		System.out.println("\n> 총 " + studentList.size() + "명의 학생을 조회합니다.");
		System.out.println("┌─────────────────────────────────────────────────────────────────┐");
		System.out.println("│      [학번]     [이름]     [학년]                         [주소]                           [전화번호]      [생년월일]   [만나이] │");

		for(int i=0 ; i<studentList.size() ; i++) {

			System.out.print("│" + (i+1) + ".");
			for(int j=0 ; (Integer.toString(i+1).length() + j) < 4 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getStudent_no());
			for(int j=0 ; (Integer.toString(studentList.get(i).getStudent_no()).length() + j) < 12 ; j++) {
				System.out.print(" ");
			}


			System.out.print(studentList.get(i).getStudent_name());
			int nameByte = getByteLength(studentList.get(i).getStudent_name());
			for(int j=0 ; nameByte + j < 14 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getStudent_year());
			for(int j=0 ; (Integer.toString(studentList.get(i).getStudent_year()).length() + j) < 5 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getStudent_addr());
			int addressByte = getByteLength(studentList.get(i).getStudent_addr());
			for(int j=0 ; addressByte + j < 55 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getStudent_tel());
			for(int j=0 ; (studentList.get(i).getStudent_tel().length() + j) < 17 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getStudent_birth());
			for(int j=0 ; (studentList.get(i).getStudent_birth().length() + j) < 16 ; j++) {
				System.out.print(" ");
			}

			System.out.print(studentList.get(i).getAge());
			for(int j=0 ; (Integer.toString(studentList.get(i).getAge()).length() + j) < 6 ; j++) {
				System.out.print(" ");
			}

			System.out.println("│");

		}
		System.out.println("└─────────────────────────────────────────────────────────────────┘");
	}

	public void printStudentListNotFound() {
		System.out.println("> 등록된 학생정보가 존재하지 않습니다.\n");
	}

	public int getSearchMenuNum(Scanner sc) {
		System.out.println("\n━━━━━━━━━< 학생정보 검색 >━━━━━━━━━");
		System.out.println("             1. 이름으로 검색                  ");
		System.out.println("             2. 학번으로 검색                  ");
		System.out.println("             3. 학년으로 검색                  ");
		System.out.println("             4. 검색 취소                      ");
		System.out.print("             선택 : ");
		return sc.nextInt();
	}

	public int getStudent_no(String msgKind, Scanner sc) {
		System.out.print("\n> " + msgKind + "학번 : ");
		return sc.nextInt();
	}

	public String getStudent_name(String msgKind, Scanner sc) {
		System.out.print("\n> " + msgKind + "이름 : ");
		return sc.next();
	}

	public int getGrade(String msgKind, Scanner sc) {
		System.out.print("\n> " + msgKind + "학년 : ");
		return sc.nextInt();
	}

	public void printSearchStudentCancel() {
		System.out.println("             학생정보 관리 화면으로 이동합니다.\n");

	}

	public void printSearchMenuNumWrong() {
		System.out.println("             잘못 선택하셨습니다.\n");
	}

	public void printSearchStudentListNotFound() {
		System.out.println("> 검색조건에 해당하는 학생정보가 존재하지 않습니다.\n");
	}

	public void printStudentNotFound(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 정보가 존재하지 않습니다.\n");
	}

	public Student getChangeStudent(Student modifyStudent, Scanner sc) {
		sc.useDelimiter(System.getProperty("line.separator"));

		System.out.println("\n> 변경할 학생 정보 입력");

		System.out.println("기존의 이름 : " + modifyStudent.getStudent_name());
		System.out.print("수정할 이름 : ");
		String stu_name = sc.next();

		System.out.println("기존의 학년 : " + modifyStudent.getStudent_year());
		System.out.print("수정할 학년 : ");
		int grade = sc.nextInt();

		System.out.println("기존의 주소 : " + modifyStudent.getStudent_addr());
		System.out.print("수정할 주소 : ");
		String address = sc.next();

		System.out.println("기존의 전화번호 : " + modifyStudent.getStudent_tel());
		System.out.print("수정할 전화번호 : ");
		String tel = sc.next();

		System.out.println("기존의 생년월일 : " + modifyStudent.getStudent_birth());
		System.out.print("수정할 생년월일 : ");
		String birth = sc.next();

		return new Student(modifyStudent.getStudent_no(), stu_name, grade, address, tel, birth);
	}

	public void printModifySuccess(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 정보가 수정되었습니다.\n");
	}

	public void printModifyFail(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 정보 수정이 실패했습니다.\n");
	}

	public void printDeleteSuccess(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 정보가 삭제되었습니다.\n");
	}

	public void printDeleteFail(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 정보 삭제가 실패했습니다.\n");
	}

	// 성적 관련 콘솔 유틸

	public void printRegistedGrade(int student_no) {
		System.out.println("> 학번 : " + student_no + " 학생의 성적은 이미 등록되어 있습니다.\n");
	}

	public Grade getNewGrade(int student_no, Scanner sc) {
    	sc.useDelimiter(System.getProperty("line.separator"));

		System.out.println("\n> 새로운 성적 정보 입력");

		System.out.print("> 국어점수 : ");
		int grade_kor = sc.nextInt();

		System.out.print("> 영어점수 : ");
		int grade_eng = sc.nextInt();

		System.out.print("> 수학점수 : ");
		int grade_math = sc.nextInt();

		return new Grade(student_no, grade_kor, grade_eng, grade_math);
	}

	public void printRegistSuccess(Grade newGrade) {
		System.out.println("> " + newGrade.getStudent_no() + "학생의 성적이 입력되었습니다.\n");
	}
	
	public void printRegistFail(Grade newGrade) {
		System.out.println("> " + newGrade.getStudent_no() + "학생의 성적입력을 실패했습니다.\n");
	}

	public void printGradeList(ArrayList<Grade> gradeList) {
		System.out.println("\n<총 " + gradeList.size() + "명의 학생 성적을 조회합니다.>");

		System.out.println("┌──────────────────────────────────────┐");

		System.out.println("│      [학번]     [이름]       [국어]    [수학]    [영어]   [총점]    [평균] │");
		for(int i=0 ; i<gradeList.size() ; i++) {

			System.out.print("│" + (i+1) + ".");
			for(int j=0 ; (Integer.toString(i+1).length() + j) < 4 ; j++) {
				System.out.print(" ");
			}

			System.out.print(gradeList.get(i).getStudent_no());
			for(int j=0 ; (Integer.toString(gradeList.get(i).getStudent_no()).length() + j) < 12 ; j++) {
				System.out.print(" ");
			}			
			
			System.out.print(gradeList.get(i).getStudent_name());
			int nameByte = getByteLength(gradeList.get(i).getStudent_name());
			for(int j=0 ; (nameByte + j) < 15 ; j++) {
				System.out.print(" ");
			}

			System.out.print(gradeList.get(i).getGrade_kor());
			for(int j=0 ; (Integer.toString(gradeList.get(i).getGrade_kor()).length() + j) < 10 ; j++) {
				System.out.print(" ");
			}

			System.out.print(gradeList.get(i).getGrade_eng());
			for(int j=0 ; (Integer.toString(gradeList.get(i).getGrade_eng()).length() + j) < 10 ; j++) {
				System.out.print(" ");
			}

			System.out.print(gradeList.get(i).getGrade_math());
			for(int j=0 ; (Integer.toString(gradeList.get(i).getGrade_math()).length() + j) < 9 ; j++) {
				System.out.print(" ");
			}

			System.out.print(gradeList.get(i).getTotal());
			for(int j=0 ; (Integer.toString(gradeList.get(i).getTotal()).length() + j) < 9 ; j++) {
				System.out.print(" ");
			}

			System.out.printf("%.1f", gradeList.get(i).getAvg());
			if(gradeList.get(i).getAvg() >= 100) {
				System.out.print(" │\n");
			}
			else if(gradeList.get(i).getAvg() >=10) {
				System.out.print("  │\n");
			}
			else {
				System.out.print("   │\n");
			}
		}
		System.out.println("└──────────────────────────────────────┘");
	}

	public void printGradeListNotFound() {
		System.out.println("> 등록된 성적정보가 존재하지 않습니다.\n");		
	}

	public int getSearchGradeMenuNum(Scanner sc) {
		System.out.println("\n━━━━━━━━━< 성적정보 검색 >━━━━━━━━━");
		System.out.println("             1. 이름으로 검색                  ");
		System.out.println("             2. 학번으로 검색                  ");
		System.out.println("             3. 학년으로 검색                  ");
		System.out.println("             4. 검색 취소                      ");
		System.out.print("             선택 : ");
		return sc.nextInt();
	}
	
	public void printSearchGradeCancel() {
		System.out.println("             성적정보 관리 화면으로 이동합니다.\n");
	}
	
	public void printSearchGradeListNotFound() {
		System.out.println("> 검색조건에 해당하는 성적정보가 존재하지 않습니다.\n");
	}

	public void printGradeNotFound(int stu_no) {
		System.out.println("> 학번 : " + stu_no + " 학생의 성적정보가 존재하지 않습니다.\n");
	}

	public Grade getChangeGrade(Grade modifyGrade, Scanner sc) {
		sc.useDelimiter(System.getProperty("line.separator"));

		System.out.println("\n> 변경할 성적 정보 입력");

		System.out.println("> 기존 국어성적 : " + modifyGrade.getGrade_kor());
		System.out.print("> 수정 국어성적 : ");
		int kor_grade = sc.nextInt();

		System.out.println("> 기존 영어성적 : " + modifyGrade.getGrade_eng());
		System.out.print("> 수정 영어성적 : ");
		int eng_grade = sc.nextInt();

		System.out.println("> 기존 수학성적 : " + modifyGrade.getGrade_math());
		System.out.print("> 수정 수학성적 : ");
		int math_grade = sc.nextInt();

		return new Grade(modifyGrade.getStudent_no(), modifyGrade.getStudent_name(), kor_grade, eng_grade, math_grade);
	}

	public void printModifySuccess(Grade changeGrade) {
		System.out.println("> 학번 : " + changeGrade.getStudent_no() + " 학생의 성적정보가 수정되었습니다.\n");
	}

	public void printModifyFail(Grade changeGrade) {
		System.out.println("> 학번 : " + changeGrade.getStudent_no() + " 학생의 성적정보 수정이 실패했습니다.\n");
	}

	public void printDeleteSuccess(Grade deleteGrade) {
		System.out.println("> 학번 : " + deleteGrade.getStudent_no() + " 학생의 성적정보가 삭제되었습니다.\n");
	}

	public void printDeleteFail(Grade deleteGrade) {
		System.out.println("> 학번 : " + deleteGrade.getStudent_no() + " 학생의 성적정보 삭제가 실패했습니다.\n");
	}

	// 장학금 관련 콘솔 유틸

	public String getScholar_name(String msgKind, Scanner sc) {
		System.out.print("\n> " + msgKind + "장학금명 : ");
		return sc.next();
	}

	public void printRegistedScholarship(String scholar_name) {
		System.out.println("> 장학금명 : " + scholar_name + " 은 이미 등록되어 있습니다.\n");
	}

	public Scholarship getNewScholarShip(String scholar_name, Scanner sc) {
		sc.useDelimiter(System.getProperty("line.separator"));

		System.out.println("\n> 새로운 장학금 정보 입력");

		System.out.print("> 백분율 : ");
		int percent = sc.nextInt();

		System.out.print("> 장학금액 : ");
		int money = sc.nextInt();

		return new Scholarship(scholar_name, percent, money);
	}

	public void printRegistSuccess(Scholarship newScholarship) {
		System.out.println("> 장학금명 : " + newScholarship.getScholar_name() + " 의 정보가 등록되었습니다.\n");
	}

	public void printRegistFail(Scholarship newScholarship) {
		System.out.println("> 장학금명 : " + newScholarship.getScholar_name() + " 의 정보 등록이 실패했습니다.\n");
	}

    public void printScholarshipList(ArrayList<Scholarship> scholarshipList) {
		System.out.println("\n<총 " + scholarshipList.size() + "종류의 장학금을 조회합니다.>");
		System.out.println("┌────────────────────────────┐");
		System.out.println("│       [장학금명]       [상위백분율]         [금액]     │");
		for(int i=0 ; i<scholarshipList.size() ; i++) {
			
			System.out.print("│" + (i+1) + ".");
			for(int j=0 ; (Integer.toString(i+1).length() + j) < 4 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipList.get(i).getScholar_name());
			int nameByte = getByteLength(scholarshipList.get(i).getScholar_name());
			for(int j=0 ; (nameByte + j) < 25 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipList.get(i).getScholar_percent());
			for(int j=0 ; (Integer.toString(scholarshipList.get(i).getScholar_percent()).length() + j) < 15 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipList.get(i).getScholar_money());
			for(int j=0 ; (Integer.toString(scholarshipList.get(i).getScholar_money()).length() + j) < 11 ; j++) {
				System.out.print(" ");
			}
			
			System.out.println("│");
		}
		System.out.println("└────────────────────────────┘");
	}

	public void printScholarshipListNotFound() {
		System.out.println("> 등록된 장학금정보가 존재하지 않습니다.\n");
	}

	public int getSearchMenuNumScholarship(Scanner sc) {
		System.out.println("\n━━━━━━━━━< 장학금종류 검색 >━━━━━━━━");
		System.out.println("             1. 장학금명으로 검색");
		System.out.println("             2. 장학금액으로 검색");
		System.out.print("             선택 : ");
		return sc.nextInt();
	}

	public int getMoney(String msgKind, Scanner sc) {
		System.out.print("\n> " + msgKind + "금액 : ");
		return sc.nextInt();
	}

	public void printSearchScholarshipListNotFound() {
		System.out.println("> 검색조건에 해당하는 장학금정보가 존재하지 않습니다.\n");
	}

	public void printScholarshipNotFound(String scholar_name) {
		System.out.println("> 장학금명 : " + scholar_name + " 정보가 존재하지 않습니다.\n");
	}

	public Scholarship getChangeScholarship(Scholarship modifyScholarship,
			Scanner sc) {

		sc.useDelimiter(System.getProperty("line.separator"));
		System.out.println("\n> 변경할 장학금 정보 입력");

		System.out.println("> 기존 백분율 : " + modifyScholarship.getScholar_percent());
		System.out.print("> 수정 백분율 : ");
		int scholar_percent = sc.nextInt();

		System.out.println("> 기존 장학금액 : " + modifyScholarship.getScholar_money());
		System.out.print("> 수정 장학금액 : ");
		int scholar_money = sc.nextInt();

		return new Scholarship(modifyScholarship.getScholar_name(), scholar_percent, scholar_money);
	}

	public void printModifySuccess(Scholarship changeScholarship) {
		System.out.println("> 장학금명 : " + changeScholarship.getScholar_name() + " 의 정보가 수정되었습니다.\n");
	}

	public void printModifyFail(Scholarship changeScholarship) {
		System.out.println("> 장학금명 : " + changeScholarship.getScholar_name() + " 의 정보 수정이 실패했습니다.\n");
	}

	public void printDeleteSuccess(Scholarship deletescholarship) {
		System.out.println("> 장학금명 : " + deletescholarship.getScholar_name() + " 의 정보가 삭제되었습니다.\n");
	}

	public void printDeleteFail(Scholarship deletescholarship) {
		System.out.println("> 장학금명 : " + deletescholarship.getScholar_name() + " 의 정보 삭제가 실패했습니다.\n");

	}

	public void printScholarshipStudentList(
			ArrayList<ScholarshipStudent> scholarshipStudentSearchList) {
				System.out.println("\n<총 " + scholarshipStudentSearchList.size() + "명의 수혜학생을 조회합니다.>");
		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("│      [학번]     [이름]       [평균]   [백분율]      [장학금명]      [장학금액] │");

		for(int i=0 ; i<scholarshipStudentSearchList.size() ; i++) {
			
			System.out.print("│" + (i+1) + ".");
			for(int j=0 ; (Integer.toString(i+1).length() + j) < 4 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipStudentSearchList.get(i).getStudent_no());
			for(int j=0 ; (Integer.toString(scholarshipStudentSearchList.get(i).getStudent_no()).length() + j) < 12 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipStudentSearchList.get(i).getStudent_name());
			int stu_noByte = getByteLength(scholarshipStudentSearchList.get(i).getStudent_name());
			for(int j=0 ; stu_noByte + j < 14 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipStudentSearchList.get(i).getAvg());
			if(scholarshipStudentSearchList.get(i).getAvg() >= 100) {
				System.out.print("     ");
			}
			else if(scholarshipStudentSearchList.get(i).getAvg() >=10) {
				System.out.print("      ");
			}
			else {
				System.out.print("       ");
			}
			
			System.out.print(scholarshipStudentSearchList.get(i).getPercent());
			if(scholarshipStudentSearchList.get(i).getPercent() >= 100) {
				System.out.print("     ");
			}
			else if(scholarshipStudentSearchList.get(i).getPercent() >=10) {
				System.out.print("      ");
			}
			else {
				System.out.print("       ");
			}
			
			System.out.print(scholarshipStudentSearchList.get(i).getScholar_name());
			int sc_nameByte = getByteLength(scholarshipStudentSearchList.get(i).getScholar_name());
			for(int j=0 ; sc_nameByte + j < 20 ; j++) {
				System.out.print(" ");
			}

			System.out.print(scholarshipStudentSearchList.get(i).getScholar_money());
			for(int j=0 ; (Integer.toString(scholarshipStudentSearchList.get(i).getScholar_money()).length() + j) < 9 ; j++) {
				System.out.print(" ");
			}
			System.out.println("│");
		}
		System.out.println("└────────────────────────────────────────┘");
	}

	public void printScholarshipStudentListNotFound() {
		System.out.println("> 등록된 장학금 수혜학생 정보가 존재하지 않습니다.\n");	
	}

}
