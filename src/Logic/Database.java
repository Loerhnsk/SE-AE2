package Logic;

import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public class Database {
    public List<TeachingRequirement> teachingRequirements;
    public List<Teacher> teacher;
    public Database(DataReader reader){
        teachingRequirements = reader.readTeachingRequirements();
        teacher = reader.readTeachers();
    }
}
