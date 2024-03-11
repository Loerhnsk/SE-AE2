package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public interface DataWriter {
    void writeTeachingRequirements(List<TeachingRequirement> teachingRequirements, String filePath);
    void writeTeachers(List<Teacher> teachers, String filePath);
    void writeAssignedRequirements(List<AssignedRequirement> assignedRequirements, String filePath);
}
