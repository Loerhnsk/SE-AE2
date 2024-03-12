package user;

import Entity.Teacher;
import Logic.BasicCommands;
import Logic.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Registration {
    private  Database database;
    public Registration() {

    }
   // private static String teacherfile ="src\\conf\\Teacher.txt";  //The file name of the teacher
  //  private static List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile(teacherfile);

    private  void showTeacher(){
        for(Teacher t:database.getDataReader().getTeachers()){
            System.out.println(t);
        }
    }
    private String del(String num){
        if(!num.matches("\\d+")) return "Wrong command";
        int id = Integer.parseInt(num);
        for(int i = 0; i < database.getDataReader().getTeachers().size(); ++i){
            if(database.getDataReader().getTeachers().get(i).getId() == id){
                if(database.getDataReader().getTeachers().get(i).checkAssign()) return "Assigned teacher. Wrong command";
                database.getDataReader().getTeachers().remove(i);
                return "Deleted";
            }
        }
        return "No such Teacher";
    }
    private boolean checkteacherID(int x){
        for(Teacher t:database.getDataReader().getTeachers()){
            if(t.getId() == x)return false;
        }
        return true;
    }
    private String register(String[] parts){

        if(parts.length != 4||!parts[0].equals("Add")) return "Wrong command";
        if(!parts[1].matches("\\d+")) return "Wrong command";
        int id = Integer.parseInt(parts[1]);
        if(!checkteacherID(id)) return "Id exist";
        String name = parts[2];
        String[] skill = parts[3].split("/");
        List<String> skills = new ArrayList<>(Arrays.asList(skill));
        Teacher nTeacher = new Teacher(name,id,skills,false,"");
        database.getDataReader().getTeachers().add(nTeacher);
        return "Register Succeed";
    }
    public void registration() {
        database = new Database();
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
                    database.getDataWriter().writeTeachers(database.getDataReader().getTeachers(), database.getDataReader().getTeacherFilePath());
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
