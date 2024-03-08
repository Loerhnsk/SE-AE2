package user;

import Entity.Teacher;
import Logic.BasicCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Registration {
    private static String teacherfile ="src\\conf\\Teacher.txt";  //The file name of the teacher
    private static List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);

    private static void showTeacher(){
        for(Teacher t:teacher){
            System.out.println(t);
        }
    }
    private static String del(String num){
        if(!num.matches("\\d+")) return "Wrong command";
        int id = Integer.parseInt(num);
        for(int i = 0; i < teacher.size(); ++i){
            if(teacher.get(i).getId() == id){
                teacher.remove(i);
                return "Deleted";
            }
        }
        return "No such Teacher";
    }
    private static boolean checkteacherID(int x){
        for(Teacher t:teacher){
            if(t.getId() == x)return false;
        }
        return true;
    }
    private static String register(String[] parts){

        if(parts.length != 3) return "Wrong command";
        if(!parts[0].matches("\\d+")) return "Wrong command";
        int id = Integer.parseInt(parts[0]);
        if(!checkteacherID(id)) return "Id exist";
        String name = parts[1];
        String[] skill = parts[2].split("/");
        List<String> skills = new ArrayList(Arrays.asList(skill));
        Teacher nTeacher = new Teacher(name,id,skills,false,"");
        teacher.add(nTeacher);
        return "Register Succeed";
    }
    public static void registration() {
        //Read from file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Main loop
        while (true) {
            try {
                //Output current state

                System.out.println("Input teacher ID, name, skills(separate with / or Enter exit to exit)");


                //Read User Input from terminal
                String userInput = reader.readLine();
                //Exit command
                if (userInput.equals("exit")) {
                    //write current data to the file
                    BasicCommands.writeTeacherToTxtFile(teacher,teacherfile);
                    break;
                }
                if (userInput.equals("SHOW")) {
                    //write current data to the file
                    System.out.println("The table of teacher");
                    showTeacher();
                    BasicCommands.writeline();
                    continue;
                }
                String[] parts = userInput.split(",",-1);
                if(parts[0].equals("Del")&&parts.length == 2)  System.out.println(del(parts[1]));
                //Input Order checking
                else System.out.println(register(parts));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
