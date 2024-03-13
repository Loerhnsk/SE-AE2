package user;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;
import Logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Director {
    private Database database;

    // Constructor for Director class
    public Director() {
        // Constructor body
    }

    // Method for approving teaching requests and assigning teachers
    private void Approvalrequest(List<TeachingRequirement> request, List<Teacher> teacher, List<AssignedRequirement> assignedRequirements,
                                 int requestID, int teacherID) {
        System.out.println("Checking requestID: " + requestID + ", teacherID: " + teacherID);
        TeachingRequirement requirement = null;
        boolean isChanged = false;

        // Iterate through teaching requirements to find the specified requestID
        for (TeachingRequirement tr : request) {
            if (tr.getRequestId() == requestID) {
                if (!tr.checkPending()) {
                    System.out.println("Not Pending");
                    return;
                } else {
                    requirement = tr;
                }
            }
        }

        // Check if the requirement was found
        if (requirement == null) {
            System.out.println("Request Not Found");
            return;
        }

        // Iterate through teachers to find the specified teacherID
        for (Teacher te : teacher) {
            if (te.getId() == teacherID) {
                if (te.checkAssign()) {
                    System.out.println("Already Assigned");
                    return;
                } else if (!te.checkSkill(requirement.getRequirement())) {
                    System.out.println("The corresponding teacher does not have this skill");
                    return;
                } else {
                    te.setAssign();
                    System.out.println(te.checkAssign());

                    // Create a new instance of AssignedRequirement
                    AssignedRequirement assignedRequirement = new AssignedRequirement();
                    assignedRequirement.setClassName(requirement.getRequirement());
                    assignedRequirement.setRequestId(requirement.getRequestId());
                    assignedRequirement.setTId(te.getId());
                    assignedRequirement.setTName(te.getName());

                    // Add the assigned requirement to the list
                    assignedRequirements.add(assignedRequirement);
                    requirement.setRequestStatus("approved");
                    System.out.println(requirement.checkPending());
                    isChanged = true;
                }
            }
        }

        // Check if any changes were made
        if (!isChanged) {
            System.out.println("Teacher Not Found");
            return;
        }

        System.out.println("Assigned");
        System.out.println("Assigned Requirements After Approval: " + assignedRequirements);
    }

    // Method for rejecting a teaching request
    public static void Rejecting(List<TeachingRequirement> list, int ID) {
        boolean isPending = false;
        boolean isChange = false;

        // Iterate through teaching requirements to find the specified ID
        for (TeachingRequirement te : list) {
            if (te.getRequestId() == ID) {
                if (te.checkPending()) {
                    te.setRequestStatus("rejected");
                    isChange = true;
                } else isPending = true;
            }
        }
    }

    // Method for outputting teachers who are not assigned
    private void outputTeacher(List<Teacher> list) {
        for (Teacher teacher : list) {
            if (!teacher.checkAssign())
                System.out.println(teacher);
        }
    }

    // Method for outputting all pending teaching requirements
    private void outputrequirement(List<TeachingRequirement> list) {
        for (TeachingRequirement tr : list) {
            if (tr.checkPending()) System.out.println(tr);
        }
    }

    // Main method for Director functionality
    public void director() {
        // Initialize BufferedReader for user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        database = new Database();

        // Output current state
        while (true) {
            try {
                System.out.println("Pending request:");
                outputrequirement(database.getDataReader().getTeachingRequirements());
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                outputTeacher(database.getDataReader().getTeachers());
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");

                // Read User Input from terminal
                String userInput = reader.readLine();

                // Exit the loop if the user inputs "exit"
                if (userInput.equals("exit")) {
                    // Write current data to the file
                    database.getDataWriter().writeTeachingRequirements(database.getDataReader().getTeachingRequirements(), database.getDataReader().getRequestFilePath());
                    database.getDataWriter().writeTeachers(database.getDataReader().getTeachers(), database.getDataReader().getTeacherFilePath());
                    database.getDataWriter().writeAssignedRequirements(database.getDataReader().getAssignedRequirements(), database.getDataReader().getAssignedFilePath());
                    break;
                }

                // Input Order checking
                String[] Order = userInput.split(",");

                if (Order.length != 2)
                    System.out.println("Wrong Order");
                else if (Order[0].equals("reject"))
                    Rejecting(database.getDataReader().getTeachingRequirements(), Integer.parseInt(Order[1]));
                else
                    Approvalrequest(database.getDataReader().getTeachingRequirements(),
                            database.getDataReader().getTeachers(),
                            database.getDataReader().getAssignedRequirements(),
                            Integer.parseInt(Order[0]), Integer.parseInt(Order[1]));

                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
