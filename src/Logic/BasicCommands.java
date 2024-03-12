package Logic;

import Entity.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicCommands {
    public static void writeTeacherToTxtFile(List<Teacher> teachers, String filePath) {
        StringBuilder txtBuilder = new StringBuilder();
        for (Teacher teacher : teachers) {
            // Add the information to the textBuilder
            txtBuilder.append(String.format("%s,%d,", teacher.getName(), teacher.getId()));
            List<String> skills = new ArrayList<>(teacher.getSkills());
            for (String skill : skills) {
                if (skill != null) txtBuilder.append(skill);
                txtBuilder.append("/");
            }
            txtBuilder.deleteCharAt(txtBuilder.length() - 1);
            if (teacher.checkAssign()) txtBuilder.append(",true,");
            else txtBuilder.append(",false,");
            txtBuilder.append(teacher.getTrain()).append("\n");
        }

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) { // false to overwrite the file
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read list from file
    public static List<Teacher> readTeacherFromTxtFile(String filePath) {
        List<Teacher> teachers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Name,Id,skill1/skill2/..,A,Assign,Training skill
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    String name = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim()); // Ensure this is an integer
                    String[] skills = parts[2].split("/");
                    List<String> skillList = new ArrayList<>(Arrays.asList(skills));
                    boolean approve = Boolean.parseBoolean(parts[3].trim());
                    String train = parts[4].trim();
                    // Add Teacher to the list
                    Teacher teacher = new Teacher(name, id, skillList, approve, train);
                    teachers.add(teacher);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing requestId to integer.");
        }
        return teachers;
    }

    // Write a line
    public static void writeline() {
        String output = "";
        for (int i = 0; i < 200; i++) output += "-";
        System.out.println(output);
    }
}
