package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Concrete implementation class
public class TxtFileDataReader extends AbstractDataReader {
    private List<TeachingRequirement> teachingRequirements;
    private List<Teacher> teacher;
    private List<AssignedRequirement> assignedRequirements;

    public TxtFileDataReader() {
        readTeachingRequirementsFromFile(ConfigManager.getRequestFilePath());
        readTeachersFromFile(ConfigManager.getTeacherFilePath());
        readAssignedRequirementsFromFile(ConfigManager.getAssignedFilePath());
    }

    @Override
    public String getRequestFilePath() {
        return ConfigManager.getRequestFilePath();
    }

    @Override
    public String getTeacherFilePath() {
        return ConfigManager.getTeacherFilePath();
    }

    @Override
    public String getAssignedFilePath() {
        return ConfigManager.getAssignedFilePath();
    }

    @Override
    protected void readTeachingRequirementsFromFile(String filePath) {
        this.teachingRequirements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assume each line format: directorName,requestId,requirement,teachingTime,requestStatus
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String className = parts[0].trim();
                    String directorName = parts[1].trim();
                    int requestId = Integer.parseInt(parts[2].trim());
                    String requirement = parts[3].trim();
                    String teachingTime = parts[4].trim();
                    String requestStatus = parts[5].trim();

                    TeachingRequirement tr = new TeachingRequirement(className, directorName, requestId, requirement,
                            teachingTime, requestStatus);
                    teachingRequirements.add(tr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing requestId to integer.");
        }
    }

    @Override
    protected void readTeachersFromFile(String filePath) {
        this.teacher = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Name,Id,skill1/skill2/..,A,Assign,Training skill
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    String Name = parts[0].trim();
                    int Id = Integer.parseInt(parts[1].trim());
                    String[] skills = parts[2].split("/");
                    List<String> list = new ArrayList<>(Arrays.asList(skills));
                    boolean Approve = Boolean.parseBoolean(parts[3].trim());
                    String Train = parts[4].trim();

                    Teacher te = new Teacher(Name, Id, list, Approve, Train);
                    teacher.add(te);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing requestId to integer.");
        }
    }

    @Override
    protected void readAssignedRequirementsFromFile(String filePath) {
        this.assignedRequirements = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assume each line format: directorName,requestId,className,TId,name
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String directorName = parts[0].trim();
                    int requestId = Integer.parseInt(parts[1].trim());
                    String className = parts[2].trim();
                    int TId = Integer.parseInt(parts[3].trim());
                    String name = parts[4].trim();

                    AssignedRequirement requirement = new AssignedRequirement();
                    requirement.setDirectorName(directorName);
                    requirement.setRequestId(requestId);
                    requirement.setClassName(className);
                    requirement.setTId(TId);
                    requirement.setTName(name);

                    assignedRequirements.add(requirement);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public List<TeachingRequirement> getTeachingRequirements() {
        return teachingRequirements;
    }

    public void setTeachingRequirements(List<TeachingRequirement> teachingRequirements) {
        this.teachingRequirements = teachingRequirements;
    }

    public List<Teacher> getTeachers() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<AssignedRequirement> getAssignedRequirements() {
        return assignedRequirements;
    }

    public void setAssignedRequirements(List<AssignedRequirement> assignedRequirements) {
        this.assignedRequirements = assignedRequirements;
    }
}
