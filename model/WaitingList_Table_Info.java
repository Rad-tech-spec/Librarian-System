package model;

/**
*<h2>Waiting List Class</h2>
* @author Rad Eshghi
* This class will be used in the data.java file and in the waiting list class<br>
* to be able to use the variables to set new values into the table. using the<br>
* default constructor.
**/

public class WaitingList_Table_Info {
	
	private int RequestNum;
	private int StudentID;
	private String StudentName;
	private String StudentPhone;
	private String Standing;
	private String Degreelvl;
	private String Date;
	private int ItemID;
	
	
	
	public WaitingList_Table_Info(int i, int j, String string, String string2, String string3, String string4,
			String string5, int k) {
		
		this.RequestNum = i;
		this.StudentID = j;
		this.StudentName = string;
		this.StudentPhone = string2;
		this.Standing = string3;
		this.Degreelvl = string4;
		this.Date = string5;
		this.ItemID = k;
	}

	public int getRequestNum() {
		return RequestNum;
	}
	
	public void setRequestNum(int requestNum) {
		this.RequestNum = requestNum;
	}
	
	public int getStudentID() {
		return StudentID;
	}
	
	public void setStudentID(int studentID) {
		this.StudentID = studentID;
	}
	
	public String getStudentName() {
		return StudentName;
	}
	
	public void setStudentName(String studentName) {
		this.StudentName = studentName;
	}
	
	public String getStudentPhone() {
		return StudentPhone;
	}
	
	public void setStudentPhone(String studentPhone) {
		this.StudentPhone = studentPhone;
	}
	
	public String getStanding() {
		return Standing;
	}
	
	public void setStanding(String standing) {
		this.Standing = standing;
	}
	
	public String getDegreelvl() {
		return Degreelvl;
	}
	
	public void setDegreelvl(String degreelvl) {
		this.Degreelvl = degreelvl;
	}
	
	public String getDate() {
		return Date;
	}
	
	public void setDate(String date) {
		this.Date = date;
	}
	
	public int getItemID() {
		return ItemID;
	}
	
	public void setItemID(int itemID) {
		this.ItemID = itemID;
	}
	
	

}
