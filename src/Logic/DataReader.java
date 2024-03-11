package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public interface DataReader {
    List<TeachingRequirement> getTeachingRequirements();
    List<Teacher> getTeachers();
    List<AssignedRequirement> getAssignedRequirements();
    String getRequestFilePath();
    String getTeacherFilePath();
    String getAssignedFilePath();
}