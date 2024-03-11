package user;

import Logic.BasicCommands;
import Entity.Teacher;
import java.util.List;

public class TeacherQueries {
    private List<Teacher> teachers;

    public TeacherQueries(String filePath) {
        this.teachers = BasicCommands.readTeacherFromTxtFile(filePath);
    }

    public void queryTeacherAssignments(String teacherName) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName)) {
                System.out.println(teacher);
            }
        }
    }
}