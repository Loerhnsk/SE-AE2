package user;

import java.io.*;
import java.util.List;

import Entity.Teacher;
import Entity.TeachingRequirement;

import Logic.BasicCommands;

public class Training {
    private static final String requestfile ="src\\conf\\Teaching_Requirement.txt";
    private static final String teacherfile ="src\\conf\\teacher.txt";

    private static void outputTeacher(List<Teacher> list){
        for (Teacher te : list) {
            if(!te.checkAssign())System.out.println(te);
        }
    }

    private static void outputrequirement(List<TeachingRequirement> list){
        for (TeachingRequirement tr : list) {
            if(tr.checkPending())System.out.println(tr);
        }
    }

    private static void Training(List<TeachingRequirement> request, List<Teacher> teacher, int requestID, int teacherID){
        TeachingRequirement requirement = null;
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
                }else {
                    if(te.checkSkill(requirement.getRequirement())){
                        System.out.println("Already Learned");
                        return;
                    }else{
                        te.setTrain(requirement.getRequirement());
                        te.setAssign();
                        requirement.setRequestStatus("approved");
                        isChanged = true;
                    }
                }
            }
        }
        if(!isChanged){
            System.out.println("Teacher Not Found");
            return;
        }
        System.out.println("Trained");

    }
    private static void Rejecting(List<TeachingRequirement> list, int ID){
            boolean isPending = false;
            boolean isChange = false;
            // 读取文件内容
            for(TeachingRequirement te:list){
                if(te.getRequestId() == ID){
                    if(te.checkPending()){
                        te.setRequestStatus("rejected");
                        isChange = true;
                    }else isPending = true;
                }
            }

            if(isPending) System.out.println("Not Pending");
            else if(!isChange) System.out.println("Request Not found");
            else System.out.println("Rejected");

    }

    public static void main(String[] args) {
        List<TeachingRequirement> teachingRequirements = BasicCommands.readTeachingRequirementsFromTxtFile(requestfile);
        List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);
        for (TeachingRequirement tr : teachingRequirements) {
            System.out.println(tr);
        }
        for (Teacher te : teacher) {
            System.out.println(te);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Pending request:");
                outputrequirement(teachingRequirements);
                System.out.println("Available Teacher");
                outputTeacher(teacher);
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    BasicCommands.writeTeachingRequirementsToTxtFile(teachingRequirements,requestfile);
                    BasicCommands.writeTeacherToTxtFile(teacher,teacherfile);
                    break;
                }
                String[] Order = userInput.split(",");
                if(Order.length != 2) System.out.println("Wrong Order");
                else if(Order[0].equals("reject"))Rejecting(teachingRequirements,Integer.parseInt(Order[1]));
                else Training(teachingRequirements,teacher,Integer.parseInt(Order[0]),Integer.parseInt(Order[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
