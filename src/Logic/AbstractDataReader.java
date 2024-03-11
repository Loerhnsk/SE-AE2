package Logic;


import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataReader implements DataReader {
    protected List<TeachingRequirement> teachingRequirements;
    protected List<Teacher> teachers;
    protected List<AssignedRequirement> assignedRequirements;
    public AbstractDataReader() {
        this.teachingRequirements = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.assignedRequirements = new ArrayList<>();
    }

    // 公共的方法
    // 具体的读取逻辑留给子类实现
    protected abstract void readTeachingRequirementsFromFile(String filePath);
    protected abstract void readTeachersFromFile(String filePath);
    protected abstract void readAssignedRequirementsFromFile(String filePath);
}