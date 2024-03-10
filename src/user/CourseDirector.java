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
                System.out.println("Enter 'exit' to exit");
                System.out.println("Enter (add,className,director,requiredSkill,time) to add a requirement");
                System.out.println("Enter (delete,ID) to delete a requirement");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    BasicCommands.writeTeachingRequirementsToTxtFile(tr, requestfile);
                    break;
                } else {
                    String[] details = userInput.split(",");
                    if (!details[0].equals("add") && !details[0].equals("delete")) {
                        System.out.println("Wrong Input! ");
                    } else if (details[0].equals("add")) {
                        TeachingRequirement newtr = new TeachingRequirement(details[1], details[2], details[3],
                                details[4]);
                        newtr.setRequestId(tr.size() + 1);
                        tr.add(newtr);
                    } else if (details[0].equals("delete")) {
                        int deleteId = Integer.parseInt(details[1]);
                        boolean found = false;
                        for (int i = 0; i < tr.size(); i++) {
                            if (tr.get(i).getRequestId() == deleteId) {
                                tr.remove(i);
                                System.out.println("Requirement with ID " + deleteId + " has been deleted.");
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Error: No requirement found with ID " + deleteId);
                        }
                        System.out.println();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}