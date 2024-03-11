package Logic;

import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public class Database {
    public List<TeachingRequirement> teachingRequirements;
    public List<Teacher> teacher;
    public Database(TxtFileDataReader reader){
        teachingRequirements = reader.readTeachingRequirements();
        teacher = reader.readTeachers();
    }
}
