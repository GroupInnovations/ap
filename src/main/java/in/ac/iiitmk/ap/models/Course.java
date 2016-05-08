package in.ac.iiitmk.ap.models;

import java.util.*;

public enum Course {
	MSC_IT ("M.Sc. IT");
	
	private String courseName;
	
	private Course (String courseName) { this.courseName = courseName; }
	
	@Override public String toString () { return this.courseName; }
	
	public static Optional<Course> toCourse (String course) {
		return Arrays.stream(Course.values())
				.filter(actualCourse -> actualCourse.toString().equals(course))
				.findFirst();
	}
}
