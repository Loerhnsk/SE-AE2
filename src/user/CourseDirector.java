package user;

import Entity.TeachingRequirement;
import Logic.BasicCommands;
import Logic.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CourseDirector {
    private Database database;

    public CourseDirector() {
    }

    private void outputallrequirement(List<TeachingRequirement> list) {
        for (TeachingRequirement tr : list) {
            System.out.println(tr);
        }
    }

    private void creatRequirement(List<TeachingRequirement> tr, String[] details) {
        TeachingRequirement newtr = new TeachingRequirement(details[1], details[2], details[3], details[4]);
        newtr.setRequestId(tr.size() + 1);
        tr.add(newtr);
    }

    private void deleteRequirement(List<TeachingRequirement> tr, String[] details) {
        if (!details[1].matches("\\d+")) {
            System.out.println("Wrong input");
            return;
        }
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
    }

    public void courseDirector() {
        Database database = new Database();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Teaching Requirements");
                outputallrequirement(database.getDataReader().getTeachingRequirements());
                BasicCommands.writeline();
                System.out.println("Enter 'exit' to exit");
                System.out.println("Enter (add,className,director,requiredSkill,time) to add a requirement");
                System.out.println("Enter (delete,ID) to delete a requirement");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    database.getDataWriter().writeTeachingRequirements(database.getDataReader().getTeachingRequirements(), database.getDataReader().getRequestFilePath());
                    break;
                } else {
                    String[] details = userInput.split(",");
                    if (!details[0].equals("add") && !details[0].equals("delete")) {
                        System.out.println("Wrong Input! ");
                    } else if (details[0].equals("add")) {
                        creatRequirement(database.getDataReader().getTeachingRequirements(), details);
                    } else if (details[0].equals("delete")) {
                        deleteRequirement(database.getDataReader().getTeachingRequirements(), details);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
