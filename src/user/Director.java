package user;

import Entity.Teacher;
import Entity.TeachingRequirement;
import Logic.BasicCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Director {
    private static String requestfile ="src\\conf\\Teaching_Requirement.txt"; //The fine name of requirement
    private static String teacherfile ="src\\conf\\Teacher.txt";  //The file name of the teacher

    private static List<TeachingRequirement> teachingRequirements = BasicCommands.readTeachingRequirementsFromTxtFile(requestfile);
    private static List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);
    public static void director() {
        //Read from file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Output current state
        while (true) {
            try {
                System.out.println("Pending request:");
                BasicCommands.outputrequirement(teachingRequirements);
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                BasicCommands.outputTeacher(teacher);
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");
                //Read User Input from terminal
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    //write current data to the file
                    BasicCommands.writeTeachingRequirementsToTxtFile(teachingRequirements, requestfile);
                    BasicCommands.writeTeacherToTxtFile(teacher, teacherfile);
                    break;
                }
                //Input Order checking
                String[] Order = userInput.split(",");
                if (Order.length != 2) System.out.println("Wrong Order");
                else if (Order[0].equals("reject")) {
                    BasicCommands.Rejecting(teachingRequirements, Integer.parseInt(Order[1]));
                } else
                    BasicCommands.Approvalrequest(teachingRequirements, teacher, Integer.parseInt(Order[0]), Integer.parseInt(Order[1]));
                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
