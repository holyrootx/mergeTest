package sms.student.vo;

public class ScholarshipStudent {
	
	private int student_no;
	private String student_name;
	private float avg;
	private String scholar_name;
	private float percent;
	private int scholar_money;
	
	
	@Override
	public String toString() {
		return "학번 : " +  student_no + ", 이름 : " + student_name + ", 평균 : " + avg + ", 장학금명 : " + scholar_name + ", 백분율 : " + percent + ", 금액 : " + scholar_money;
	}
	
	
	public ScholarshipStudent(int student_no, String student_name, float avg,
			String scholar_name, float percent, int scholar_money) {
		super();
		this.student_no = student_no;
		this.student_name = student_name;
		this.avg = calculateAvg(avg);
		this.scholar_name = scholar_name;
		this.percent = percent;
		this.scholar_money = scholar_money;
	}
	
	private float calculateAvg(float avg) {
		avg = (int)(avg * 10 + 0.5f) / 10f;
		return avg;
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

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public String getScholar_name() {
		return scholar_name;
	}

	public void setScholar_name(String scholar_name) {
		this.scholar_name = scholar_name;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public int getScholar_money() {
		return scholar_money;
	}

	public void setScholar_money(int scholar_money) {
		this.scholar_money = scholar_money;
	}
	

}
