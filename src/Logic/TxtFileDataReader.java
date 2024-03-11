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

// 具体的实现类
public class TxtFileDataReader extends AbstractDataReader {

    @Override
    public List<TeachingRequirement> readTeachingRequirements() {
        String filePath = ConfigManager.getRequestFilePath();
        readTeachingRequirementsFromFile(filePath);
        return teachingRequirements;
    }

    @Override
    public List<Teacher> readTeachers() {
        String filePath = ConfigManager.getTeacherFilePath();
        readTeachersFromFile(filePath);
        return teachers;
    }

    @Override
    public List<AssignedRequirement> readAssignedRequirements() {
        String filePath = ConfigManager.getAssignedFilePath();
        readAssignedRequirementsFromFile(filePath);
        return assignedRequirements;
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
        teachingRequirements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设每行格式为: directorName,requestId,requirement,teachingTime,requestStatus
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String className = parts[0].trim();
                    String directorName = parts[1].trim();
                    int requestId = Integer.parseInt(parts[2].trim()); // 确保这是一个整数
                    String requirement = parts[3].trim();
                    String teachingTime = parts[4].trim();
                    String requestStatus = parts[5].trim();

                    // 创建TeachingRequirement对象并添加到列表中
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
        teachers = new ArrayList<>(); // 使用类的成员变量

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Name,Id,skill1/skill2/..,A,Assign,Training skill
                String[] parts = line.split(",",-1);
                if (parts.length >= 5) {
                    String Name = parts[0].trim();
                    int Id = Integer.parseInt(parts[1].trim()); // 确保这是一个整数
                    String[] skills = parts[2].split("/");
                    List<String> list = new ArrayList<>(Arrays.asList(skills));
                    boolean Approve = Boolean.parseBoolean(parts[3].trim());
                    String Train = parts[4].trim();
                    // Add Teacher to the list
                    Teacher te = new Teacher(Name,Id,list,Approve,Train);
                    teachers.add(te);
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
        assignedRequirements = new ArrayList<>(); // 使用类的成员变量

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设每行格式为: directorName,requestId,className,TId,name
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String directorName = parts[0].trim();
                    int requestId = Integer.parseInt(parts[1].trim());
                    String className = parts[2].trim();
                    int TId = Integer.parseInt(parts[3].trim());
                    String name = parts[4].trim();

                    // 创建AssignedRequirement对象并添加到列表中
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
}
