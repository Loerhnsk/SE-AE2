package user;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;
import Logic.Database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Search {
    private Database database;

    // Constructor for Search class
    public Search() {
        // Constructor body
    }

    // Main method for searching functionality
    public void search() {
        // Initialize Database and BufferedReader for user input
        database = new Database();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter 'Director,directorName' to search by Director's name.");
            System.out.println("Enter 'Teacher,teacherId' to search by Teacher's ID.");
            System.out.println("Enter 'exit' to exit.");

            // Read user input in a loop until 'exit' is entered
            String input = reader.readLine();
            while (!"exit".equalsIgnoreCase(input)) {
                String[] parts = input.split(",", 2);

                // Check the input format and perform the corresponding search
                if (parts.length == 2) {
                    if ("Director".equalsIgnoreCase(parts[0])) {
                        searchByDirectorName(parts[1], database.getDataReader().getTeachingRequirements(), database.getDataReader().getAssignedRequirements());
                    } else if ("Teacher".equalsIgnoreCase(parts[0])) {
                        try {
                            int teacherId = Integer.parseInt(parts[1]);
                            searchByTeacherId(teacherId, database.getDataReader().getTeachers(), database.getDataReader().getAssignedRequirements());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Teacher ID must be an integer.");
                        }
                    } else {
                        System.out.println("Invalid command, please try again.");
                    }
                } else {
                    System.out.println("Invalid input format. Please follow the instructions.");
                }

                // Read the next user input
                input = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method for searching by Director's name
    private void searchByDirectorName(String directorName, List<TeachingRequirement> teachingRequirements, List<AssignedRequirement> assignedRequirements) {
        System.out.println("Searching for Director: " + directorName);

        // Filter and print teaching requirements matching the Director's name
        teachingRequirements.stream()
                .filter(tr -> tr.getDirectorName().equalsIgnoreCase(directorName))
                .forEach(tr -> {
                    System.out.println(tr);

                    // Check and print assigned teacher information
                    assignedRequirements.stream()
                            .filter(ar -> ar.getRequestId() == tr.getRequestId())
                            .findFirst()
                            .ifPresentOrElse(
                                    ar -> System.out.println("Assigned to Teacher ID: " + ar.getTId()),
                                    () -> System.out.println("Not Assigned")
                            );
                });
    }

    // Method for searching by Teacher's ID
    private void searchByTeacherId(int teacherId, List<Teacher> teachers, List<AssignedRequirement> assignedRequirements) {
        System.out.println("Searching for Teacher ID: " + teacherId);

        // Attempt to find a matching teacher
        Teacher foundTeacher = teachers.stream()
                .filter(t -> t.getId() == teacherId)
                .findFirst()
                .orElse(null);

        // Print teacher information and assigned requirements
        if (foundTeacher != null) {
            System.out.println(foundTeacher);

            // Display assigned requirements for the teacher
            boolean hasAssignedWork = false;
            for (AssignedRequirement ar : assignedRequirements) {
                if (ar.getTId() == teacherId) {
                    hasAssignedWork = true;
                    System.out.println("Assigned Requirement: " + ar);
                }
            }
            if (!hasAssignedWork) {
                System.out.println("No assigned work for this teacher.");
            }

            // Display training information for the teacher
            if (foundTeacher.getTrain() != null && !foundTeacher.getTrain().isEmpty()) {
                System.out.println("Training arranged for this teacher: " + foundTeacher.getTrain());
            } else {
                System.out.println("No training arranged for this teacher.");
            }
        } else {
            System.out.println("No teacher found with ID: " + teacherId);
        }
    }
}
