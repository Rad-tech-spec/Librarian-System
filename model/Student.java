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
	private String academicStanding = "";
	private String degreeLevel = "";
	
	/**
	 * Creates an empty instance of <i>Student</i> class.
	 */
	public Student() { }
	
	/**
	 * Creates an instance of <i>Student</i> with provided values.
	 * 
	 * @param studentId Student ID
	 * @param studentName Student's Full Name
	 * @param studentPhone Student's Phone
	 * @param currentStudent Student's academic standing (current student or alumni)
	 * @param degreeLevel Student's academic level (undergrad or grad)
	 */
	public Student(String studentId, String studentName, String studentPhone, String academicStanding, String degreeLevel) {
		setStudentId(studentId);
		setStudentName(studentName);
		setStudentPhone(studentPhone);
		setAcademicStanding(academicStanding);
		setDegreeLevel(degreeLevel);
	}
	
	/**
	 * @param studentId Student ID
	 */
	public void setStudentId(String studentId) { this.studentId = studentId; }

	/**
	 * @return Student ID
	 */
	public String getStudentId() { return studentId; }

	/**
	 * @param studentName Student's Full Name
	 */
	public void setStudentName(String studentName) { this.studentName = studentName; }

	/**
	 * @return Student's Full Name
	 */
	public String getStudentName() { return studentName; }

	/**
	 * @param studentPhone Student's Phone
	 */
	public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }

	/**
	 * @return Student's Phone
	 */
	public String getStudentPhone() { return studentPhone; }

	/**
	 * @param academicStanding Student's academic standing (Current Student or Alumni)
	 */
	public void setAcademicStanding(String academicStanding) { this.academicStanding = academicStanding; }

	/**
	 * @return Student's academic standing (Current Student or Alumni)
	 */
	public String getAcademicStanding() { return academicStanding; }

	/**
	 * @param degreeLevel Student's degree level (undergrad or grad)
	 */
	public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }

	/**
	 * @return Student's degree level (undergrad or grad)
	 */
	public String getDegreeLevel() { return degreeLevel; }
}
