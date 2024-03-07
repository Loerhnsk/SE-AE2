package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entity.AssignedRequirement;
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
    public static void writeTeacherToTxtFile(List<Teacher> teacher,
                                                          String filePath) {
        StringBuilder txtBuilder = new StringBuilder();
        for (Teacher t_e : teacher) {
            //Add the information to the textBuilder
            txtBuilder.append(String.format("%s,%d,",
                    t_e.getName(),
                    t_e.getId()));
            List<String> skill = new ArrayList<>(t_e.getSkills());
            for (String s : skill) {
                if(s != null)txtBuilder.append(s);
                txtBuilder.append("/");
            }
            txtBuilder.deleteCharAt(txtBuilder.length() - 1);
            if(t_e.checkAssign())txtBuilder.append(",true,");
            else txtBuilder.append(",false,");
            txtBuilder.append(t_e.getTrain()).append("\n");
        }

        // write to the file
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

    //List from File
    public static List<Teacher> readTeacherFromTxtFile(String filePath) {
        List<Teacher> teacher = new ArrayList<>();
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
    public static List<AssignedRequirement> readAssignedRequirementsFromTxtFile(String filePath) {
        List<AssignedRequirement> assignedRequirements = new ArrayList<>();

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

        return assignedRequirements;
    }

    //write a line
    public static void writeline(){

        String output = "";
        for(int i=0; i<200 ;i++) output +="-";
        System.out.println(output);
    }
    //用于输出teacher
    public static void outputTeacher(List<Teacher> list){
        for (Teacher teacher : list) {
            if(!teacher.checkAssign())
                System.out.println(teacher);
        }
    }
    //output all of the pending requirement
    public static void outputrequirement(List<TeachingRequirement> list){
        for (TeachingRequirement tr : list) {
            if(tr.checkPending())System.out.println(tr);
        }
    }
//    Use teacher ID and approval ID to make approval request
    public static void Approvalrequest(List<TeachingRequirement> request, List<Teacher> teacher, List<AssignedRequirement> assignedRequirements,
                                       int requestID, int teacherID){
        TeachingRequirement requirement = null;
        AssignedRequirement assignedRequirement = new AssignedRequirement();
        boolean isChanged = false;
        boolean Notfound = true;
        for(TeachingRequirement tr:request){
            if(tr.getRequestId() == requestID){
                if(!tr.checkPending()){
                    System.out.println("Not Pending");
                    return;
                }else {
                    requirement = tr;
                    Notfound = false;
                }
            }
        }
        if (Notfound) {
            System.out.println("Request Not Found");
            return;
        }
        for(Teacher te:teacher){
            if(te.getId() == teacherID){
                if(te.checkAssign()){
                    System.out.println("Already Assigned");
                    return;
                }else{
                        te.setAssign();
                        te.setAssigned(requirement.getClassName());
                        assignedRequirement.setClassName(requirement.getClassName());
                        assignedRequirement.setRequestId(requirement.getRequestId());
                        assignedRequirement.setTId(te.getId());
                        assignedRequirement.setTName(te.getName());
                        assignedRequirements.add(assignedRequirement);
                        requirement.setRequestStatus("approved");
                        isChanged = true;
                }
            }
        }
        if(!isChanged){
            System.out.println("Teacher Not Found");
            return;
        }
        System.out.println("Assigned");

    }
    public static void Rejecting(List<TeachingRequirement> list, int ID) {
        boolean isPending = false;
        boolean isChange = false;
        // 读取文件内容
        for (TeachingRequirement te : list) {
            if (te.getRequestId() == ID) {
                if (te.checkPending()) {
                    te.setRequestStatus("rejected");
                    isChange = true;
                } else isPending = true;
            }
        }
    }
    public static void writeAssignedRequirementsToTxtFile(List<AssignedRequirement> assignedRequirements,
                                                          String filePath) {
        StringBuilder txtBuilder = new StringBuilder();
        for (AssignedRequirement requirement : assignedRequirements) {
            // 格式化字符串并追加到StringBuilder
            txtBuilder.append(String.format("%s,%s,%d,%s,%s\n",
                    requirement.getDirectorName(),
                    requirement.getRequestId(),
                    requirement.getClassName(),
                    requirement.getTId(),
                    requirement.getTName()));
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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