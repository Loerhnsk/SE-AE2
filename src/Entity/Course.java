package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容

    private String courseId;
    private String courseName;
    private List<String> requiredSkills;
    private List<Teacher> assignedTeachers;

    // 构造器
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.requiredSkills = new ArrayList<>();
        this.assignedTeachers = new ArrayList<>();
    }


    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void addRequiredSkill(String skill) {
        this.requiredSkills.add(skill);
    }


    public List<Teacher> getAssignedTeachers() {
        return assignedTeachers;
    }

    public void setAssignedTeachers(List<Teacher> assignedTeachers) {
        this.assignedTeachers = assignedTeachers;
    }

    public void assignTeacher(Teacher teacher) {
        this.assignedTeachers.add(teacher);
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", requiredSkills=" + requiredSkills +
                ", assignedTeachers=" + assignedTeachers +
                '}';
    }
}
