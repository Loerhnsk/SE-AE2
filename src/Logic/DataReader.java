package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public interface DataReader {
    List<TeachingRequirement> readTeachingRequirements();
    List<Teacher> readTeachers();
    List<AssignedRequirement> readAssignedRequirements();
    String getRequestFilePath();
    String getTeacherFilePath();
    String getAssignedFilePath();
}