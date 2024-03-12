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
    public Search(){}
    public void search() {
        // 在方法开始时读取所有数据
        database = new Database();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter 'Director,directorName' to search by Director's name.");
            System.out.println("Enter 'Teacher,teacherId' to search by Teacher's ID.");
            System.out.println("Enter 'exit' to exit.");

            String input = reader.readLine();

            while (!"exit".equalsIgnoreCase(input)) {
                String[] parts = input.split(",", 2);
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
                input = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByDirectorName(String directorName, List<TeachingRequirement> teachingRequirements, List<AssignedRequirement> assignedRequirements) {
        System.out.println("Searching for Director: " + directorName);
        teachingRequirements.stream()
                .filter(tr -> tr.getDirectorName().equalsIgnoreCase(directorName))
                .forEach(tr -> {
                    System.out.println(tr);
                    assignedRequirements.stream()
                            .filter(ar -> ar.getRequestId() == tr.getRequestId())
                            .findFirst()
                            .ifPresentOrElse(
                                    ar -> System.out.println("Assigned to Teacher ID: " + ar.getTId()),
                                    () -> System.out.println("Not Assigned")
                            );
                });
    }

    private void searchByTeacherId(int teacherId, List<Teacher> teachers, List<AssignedRequirement> assignedRequirements) {
        System.out.println("Searching for Teacher ID: " + teacherId);
        // 尝试找到匹配的教师
        Teacher foundTeacher = teachers.stream()
                .filter(t -> t.getId() == teacherId)
                .findFirst()
                .orElse(null);
        if (foundTeacher != null) {
            // 打印教师信息
            System.out.println(foundTeacher);
            // 显示该教师被分配的所有教学需求
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
            // 显示教师被安排的培训信息
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