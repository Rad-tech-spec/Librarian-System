package model;

/**
 * This class represents a student requesting an item in the <i>Library</i>.
 * 
 * @author Nikita Mezhenskyi
 * @version 1.0.0, 2021-03-07
 */
public class Student {
	private String studentId = "";
	private String studentName = "";
	private String studentPhone = "";
	private boolean currentStudent = true;
	private String degreeLevel = "";
	
	/**
	 * Creates an empty instance of <i>Student</i> class.
	 */
	public Student() { }
	
	/**
	 * Creates an instance of <i>Student</i> with provided values.
	 * @param studentId Student ID
	 * @param studentName Student's Full Name
	 * @param studentPhone Student's Phone
	 * @param currentStudent Student's academic standing (current student or alumni)
	 * @param underGrad Student's academic level (undergrad or grad)
	 */
	public Student(String studentId, String studentName, String studentPhone, boolean currentStudent, String degreeLevel) {
		setStudentId(studentId);
		setStudentName(studentName);
		setStudentPhone(studentPhone);
		setCurrentStudent(currentStudent);
		setDegreeLevel(degreeLevel);
	}
	
	public void setStudentId(String studentId) { this.studentId = studentId; }

	public String getStudentId() { return studentId; }

	public void setStudentName(String studentName) { this.studentName = studentName; }

	public String getStudentName() { return studentName; }

	public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }

	public String getStudentPhone() { return studentPhone; }

	public void setCurrentStudent(boolean currentStudent) { this.currentStudent = currentStudent; }

	public boolean isCurrentStudent() { return currentStudent; }

	public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }

	public String getDegreeLevel() { return degreeLevel; }
}
