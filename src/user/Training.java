package user;

import java.io.*;
import java.util.List;

import Entity.Teacher;
import Entity.TeachingRequirement;

import Logic.BasicCommands;

public class Training {
    private static String requestfile ="src\\conf\\Teaching_Requirement.txt"; //The fine name of requirement
    private static String teacherfile ="src\\conf\\Teacher.txt";  //The file name of the teacher

    private static List<TeachingRequirement> teachingRequirements = BasicCommands.readTeachingRequirementsFromTxtFile(requestfile);
    private static List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);
    //output all of available teacher

  //  public Training(String requestfile, String teacherfile){
   //     teachingRequirements = BasicCommands.readTeachingRequirementsFromTxtFile(requestfile);
   //     teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);
   // }
    private static void outputTeacher(List<Teacher> list){
        for (Teacher te : list) {
            if(!te.checkAssign())System.out.println(te);
        }
    }

    //output all of the pending requirement
    private static void outputrequirement(List<TeachingRequirement> list){
        for (TeachingRequirement tr : list) {
            if(tr.checkPending())System.out.println(tr);
        }
    }

    //Training a teacher(teacher ID) for the skill of request(requestID)
    private static void Train(List<TeachingRequirement> request, List<Teacher> teacher, int requestID, int teacherID){
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

    //Order of rejecting a requirement with ID
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

    public static void training() {
        //Read from file

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Main loop
        while (true) {
            try {
                //Output current state
                System.out.println("Pending request:");
                outputrequirement(teachingRequirements);
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                outputTeacher(teacher);
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");
                //Read User Input from terminal
                String userInput = reader.readLine();
                //Exit command
                if (userInput.equals("exit")) {
                    //write current data to the file
                    BasicCommands.writeTeachingRequirementsToTxtFile(teachingRequirements,requestfile);
                    BasicCommands.writeTeacherToTxtFile(teacher,teacherfile);
                    break;
                }
                //Input Order checking
                String[] Order = userInput.split(",");
                if(Order.length != 2) System.out.println("Wrong Order");
                else if(Order[0].equals("reject"))Rejecting(teachingRequirements,Integer.parseInt(Order[1]));
                else Train(teachingRequirements,teacher,Integer.parseInt(Order[0]),Integer.parseInt(Order[1]));
                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
