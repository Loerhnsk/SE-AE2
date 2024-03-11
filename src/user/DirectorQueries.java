package user;

import Logic.BasicCommands;
import Entity.TeachingRequirement;
import java.util.List;

public class DirectorQueries {
    private List<TeachingRequirement> requirements;

    public DirectorQueries(String filePath) {
        this.requirements = BasicCommands.readTeachingRequirementsFromTxtFile(filePath);
    }

    public void queryDirectorRequirements(String directorName) {
        for (TeachingRequirement req : requirements) {
            if (req.getDirectorName().equals(directorName)) {
                System.out.println(req);
            }
        }
    }
}