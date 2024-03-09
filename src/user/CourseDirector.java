package user;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;
import Logic.BasicCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CourseDirector {
    private static String requestfile = "src\\conf\\Teaching_Requirement.txt"; // The fine name of requirement
    private static String teacherfile = "src\\conf\\Teacher.txt"; // The file name of the teacher
    private static String assignedfile = "src\\conf\\Assigned_Requirement.txt";// The file name of the assigned request
    private static List<TeachingRequirement> teachingRequirements = BasicCommands
            .readTeachingRequirementsFromTxtFile(requestfile);
    private static List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);
    private static List<AssignedRequirement> assigned = BasicCommands.readAssignedRequirementsFromTxtFile(assignedfile);

    public static void courseDirector() {
        List<TeachingRequirement> tr = BasicCommands.readTeachingRequirementsFromTxtFile(requestfile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Teaching Requirements");
                BasicCommands.outputallrequirement(tr);
                BasicCommands.writeline();
                System.out.print("Enter 'exit' to exit, or enter 'add' to add a requirement: ");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    BasicCommands.writeTeachingRequirementsToTxtFile(tr, requestfile);
                    break;
                } else if (userInput.equals("add")) {
                    System.out.print("Enter the requirement (className,director,requiredSkill,time): ");
                    String requirementInput = reader.readLine(); 
                    String[] details = requirementInput.split(","); 
                    if (details.length != 4) {
                        System.out.println("Wrong Input! Please use the format: className,director,requiredSkill,time");
                    } else {                    
                        TeachingRequirement newtr = new TeachingRequirement(details[0], details[1],details[2], details[3]);
                        newtr.setRequestId(tr.size()+1);
                        tr.add(newtr);
                        BasicCommands.writeTeachingRequirementsToTxtFile(tr,requestfile);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}