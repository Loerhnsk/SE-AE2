package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Entity.Teacher;
import Entity.TeachingRequirement;
import Logic.BasicCommands;
import Logic.Database;

public class Training {

    private static Database database;

    // Constructor for Training class
    public Training() {}

    private String name;
    private int id;
    private String skill;

    // Output teachers who are not assigned
    private void outputTeacher(List<Teacher> list){
        for (Teacher te : list) {
            if(!te.checkAssign()) System.out.println(te);
        }
    }

    // Output all pending teaching requirements
    private void outputrequirement(List<TeachingRequirement> list){
        for (TeachingRequirement tr : list) {
            if(tr.checkPending()) System.out.println(tr);
        }
    }

    // Train a teacher (teacher ID) for the skill of a request (requestID)
    private void Train(List<TeachingRequirement> request, List<Teacher> teacher, int requestID, int teacherID){
        TeachingRequirement requirement = null;
        boolean isChanged = false;
        boolean notFound = true;

        // Iterate through teaching requirements to find the specified requestID
        for(TeachingRequirement tr: request){
            if(tr.getRequestId() == requestID){
                if(!tr.checkPending()){
                    System.out.println("Not Pending");
                    return;
                } else {
                    requirement = tr;
                    notFound = false;
                }
            }
        }

        // Check if the requirement was found
        if (notFound) {
            System.out.println("Request Not Found");
            return;
        }

        // Iterate through teachers to find the specified teacherID
        for(Teacher te: teacher){
            if(te.getId() == teacherID){
                if(te.checkAssign()){
                    System.out.println("Already Assigned");
                    return;
                } else {
                    if(te.checkSkill(requirement.getRequirement())){
                        System.out.println("Already Learned");
                        return;
                    } else {
                        te.setTrain(requirement.getRequirement());
                        te.setAssign();
                        requirement.setRequestStatus("approved");
                        isChanged = true;
                    }
                }
            }
        }

        // Check if any changes were made
        if(!isChanged){
            System.out.println("Teacher Not Found");
            return;
        }
        System.out.println("Trained");
    }

    // Reject a requirement with a specific ID
    private void Rejecting(List<TeachingRequirement> list, int ID){
        boolean isPending = false;
        boolean isChange = false;

        // Iterate through teaching requirements to find the specified ID
        for(TeachingRequirement te: list){
            if(te.getRequestId() == ID){
                if(te.checkPending()){
                    te.setRequestStatus("rejected");
                    isChange = true;
                } else isPending = true;
            }
        }

        // Print appropriate messages based on the rejection status
        if(isPending) System.out.println("Not Pending");
        else if(!isChange) System.out.println("Request Not Found");
        else System.out.println("Rejected");
    }

    // Main method for Training functionality
    public void training() {
        // Read from file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        database = new Database();

        // Main loop
        while (true) {
            try {
                // Output current state
                System.out.println("Pending request:");
                outputrequirement(database.getDataReader().getTeachingRequirements());
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                outputTeacher(database.getDataReader().getTeachers());
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");

                // Read User Input from terminal
                String userInput = reader.readLine();

                // Exit command
                if (userInput.equals("exit")) {
                    // Write current data to the file
                    database.getDataWriter().writeTeachingRequirements(database.getDataReader().getTeachingRequirements(), database.getDataReader().getRequestFilePath());
                    database.getDataWriter().writeTeachers(database.getDataReader().getTeachers(), database.getDataReader().getTeacherFilePath());
                    break;
                }

                // Input Order checking
                String[] Order = userInput.split(",");
                if(Order.length != 2) System.out.println("Wrong Order");
                else if(Order[0].equals("reject")) Rejecting(database.getDataReader().getTeachingRequirements(),Integer.parseInt(Order[1]));
                else Train(database.getDataReader().getTeachingRequirements(), database.getDataReader().getTeachers(), Integer.parseInt(Order[0]),Integer.parseInt(Order[1]));
                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

