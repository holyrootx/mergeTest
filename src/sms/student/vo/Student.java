package sms.student.vo;

import java.util.Calendar;

import sms.student.svc.StudentRegistService;
import sms.student.util.ConsoleUtil;

public class Student {

	private int student_no;
	private String student_name;
	private int student_year;
	private String student_addr;
	private String student_tel;
	private String student_birth;
	private int age;

	@Override
	public String toString() {
		return student_no + "  " + student_name + "  " + student_year + "  "  + student_addr + "  "  + student_tel + "  "  + student_birth + "  "  + age;
	}

	public Student(int student_no, String student_name, int student_year,
			String student_addr, String student_tel, String student_birth) {
		super();
		this.student_no = student_no;
		this.student_name = student_name;
		this.student_year = student_year;
		this.student_addr = student_addr;
		this.student_tel = student_tel;
		this.student_birth = student_birth;
		this.age = calculateAge(student_birth);
	}

	private int calculateAge(String student_birth) {
		StudentRegistService studentRegistService = new StudentRegistService();
		String unfitForm = studentRegistService.compareBirthRegistForm(this);

		if(unfitForm != null) {
			return 0;
		}
		
		int firstHyphenIndex = student_birth.indexOf("-");
		int secondHyphenIndex = student_birth.indexOf("-", firstHyphenIndex+1);
		int birthYear = Integer.parseInt(student_birth.substring(0, firstHyphenIndex));
		int birthMonth = Integer.parseInt(student_birth.substring(firstHyphenIndex+1, secondHyphenIndex));
		int birthDay = Integer.parseInt(student_birth.substring(secondHyphenIndex+1));
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		int nowMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int nowDay = Calendar.getInstance().get(Calendar.DATE);
		int age = nowYear-birthYear;
		if(nowMonth*100 + nowDay < birthMonth*100 + birthDay){
			age--;
		}
		return age;
	}

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public int getStudent_year() {
		return student_year;
	}

	public void setStudent_year(int student_year) {
		this.student_year = student_year;
	}

	public String getStudent_addr() {
		return student_addr;
	}

	public void setStudent_addr(String student_addr) {
		this.student_addr = student_addr;
	}

	public String getStudent_tel() {
		return student_tel;
	}

	public void setStudent_tel(String student_tel) {
		this.student_tel = student_tel;
	}

	public String getStudent_birth() {
		return student_birth;
	}

	public void setStudent_birth(String student_birth) {
		this.student_birth = student_birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
