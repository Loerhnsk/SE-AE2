package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entity.Teacher;
import Entity.TeachingRequirement; // 确保引入了正确的TeachingRequirement类

public class BasicCommands {

    // 输入一个教学需求的List，将它们全部写入到txt中
    public static void writeTeachingRequirementsToTxtFile(List<TeachingRequirement> teachingRequirements,
                                                          String filePath) {
        StringBuilder txtBuilder = new StringBuilder();
        for (TeachingRequirement requirement : teachingRequirements) {
            // 格式化字符串并追加到StringBuilder
            txtBuilder.append(String.format("%s,%s,%d,%s,%s,%s\n",
                    requirement.getClassName(),
                    requirement.getDirectorName(),
                    requirement.getRequestId(),
                    requirement.getRequirement(),
                    requirement.getTeachingTime(),
                    requirement.getRequestStatus()));
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) { // false to overwrite the
            // file
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取txt，创建出对应的教学需求List

    public static List<TeachingRequirement> readTeachingRequirementsFromTxtFile(String filePath) {
        List<TeachingRequirement> teachingRequirements = new ArrayList<>();
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
        return teachingRequirements;
    }
    public static List<Teacher> readTeacherFromTxtFile(String filePath) {
        List<Teacher> teacher = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设每行格式为: directorName,requestId,requirement,teachingTime,requestStatus
                String[] parts = line.split(",",-1);
                if (parts.length >= 5) {
                    String Name = parts[0].trim();
                    int Id = Integer.parseInt(parts[1].trim()); // 确保这是一个整数
                    String[] skills = parts[2].split("/");
                    List<String> list = new ArrayList<>(Arrays.asList(skills));
                    boolean Approve = Boolean.parseBoolean(parts[3].trim());
                    String Train = parts[4].trim();
                    // 创建TeachingRequirement对象并添加到列表中
                    Teacher te = new Teacher(Name,Id,list,Approve,Train);
                    teacher.add(te);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing requestId to integer.");
        }
        return teacher;
    }

    public static List<Teacher> readTeachersFromTxtFile(String filePath) {
        List<Teacher> teachers = new ArrayList<>();
        boolean skipHeader = true;  // 添加一个标志来跳过标题行

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 跳过标题行
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // 处理实际数据
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String name = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    List<String> teachingSkills = Arrays.asList(parts[2].split("/"));
                    boolean assigned = Boolean.parseBoolean(parts[3].trim());
                    String trainingSkill = parts[4].trim();

                    Teacher teacher = new Teacher(name, id, teachingSkills, assigned, trainingSkill);
                    teachers.add(teacher);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return teachers;
    }
    public static void writeTeachersToTxtFile(List<Teacher> teachers, String filePath) {
        StringBuilder txtBuilder = new StringBuilder();
        for (Teacher teacher : teachers) {
            // 格式化字符串并追加到StringBuilder
            txtBuilder.append(String.format("%s,%d,%s,%s,%s\n",
                    teacher.getName(),
                    teacher.getId(),
                    String.join("/", teacher.getSkills()),  // 将教学技能列表转为用 '/' 分隔的字符串
                    teacher.checkAssigned(),
                    teacher.getTrain()));
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) { // false to overwrite the file
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void approveTeachingRequestAndAssignTeacher(List<TeachingRequirement> teachingRequirements,
                                                              List<Teacher> teachers, int requestId, int teacherId) {
        // 查找对应的教学请求和教师
        TeachingRequirement selectedRequest = null;
        Teacher selectedTeacher = null;

        for (TeachingRequirement request : teachingRequirements) {
            if (request.getRequestId() == requestId) {
                selectedRequest = request;
                break;
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                selectedTeacher = teacher;
                break;
            }
        }
        // 检查是否找到了对应的教学请求和教师
        if (selectedRequest == null || selectedTeacher == null) {
            System.out.println("Teaching request or teacher not found.");
            return;
        }
        // 检查教师是否已分配任务
        if (selectedTeacher.checkAssigned()) {
            System.out.println("Teacher is already assigned to a task.");
            return;
        }
        // 更新教学请求状态和教师分配状态
        selectedRequest.setRequestStatus("approved");
        selectedTeacher.setAssigned();

        // 将更新后的信息写回文件
        writeTeachingRequirementsToTxtFile(teachingRequirements, "src\\conf\\Teaching_Requirement.txt");
        writeTeachersToTxtFile(teachers, "src\\conf\\teacher.txt");

        System.out.println("Teaching request approved, and teacher assigned.");
    }
    public static void main(String[] args) {
        //List<TeachingRequirement> teachingRequirements = List.of(
        //         new TeachingRequirement("Jesus", "John Doe", 1, "Mathematics", "semester 1", "pending"),
        //         new TeachingRequirement("kyaru", "Jane Smith", 2, "English", "semester 2", "approved")
        // 确保TeachingRequirement类构造器和方法与此调用相匹配
        //  );
        String filePath = "src\\conf\\Teaching_Requirement.txt";
        // writeTeachingRequirementsToTxtFile(teachingRequirements, filePath);

        List<TeachingRequirement> teachingRequirements1 = readTeachingRequirementsFromTxtFile(filePath);
        for (TeachingRequirement tr : teachingRequirements1) {
            System.out.println(tr);
        }
    }
}