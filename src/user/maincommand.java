package user;


import Logic.DataReader;
import Logic.TxtFileDataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class maincommand {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 创建 Director 实例并注入数据读取实现类
        DataReader dataReader = new TxtFileDataReader();
        Director director = new Director(dataReader);

        String teachingRequirementsPath = "src/conf/Teaching_Requirement.txt";
        String teacherPath = "src/conf/Teacher.txt";

//        DirectorQueries directorQueries = new DirectorQueries(teachingRequirementsPath);
//        TeacherQueries teacherQueries = new TeacherQueries(teacherPath);
while(true){
            try {
                //Output current state
                System.out.print("Command:");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    break;
                }
                if (userInput.equals("Training")) {
                    Training.training();
                }
                if(userInput.equals("Director"))
                {
                    director.director();
                }
                if (userInput.equals("Registration")){
                    Registration.registration();
                }
                if (userInput.equals("CourseDirector")){
                    CourseDirector.courseDirector();
                }
//                if (userInput.equals("QueryDirector")) {
//                    System.out.println("Enter Director's Name:");
//                    String directorName = reader.readLine();
//                    directorQueries.queryDirectorRequirements(directorName);
//                }
//                if (userInput.equals("QueryTeacher")) {
//                    System.out.println("Enter Teacher's Name:");
//                    String teacherName = reader.readLine();
//                    teacherQueries.queryTeacherAssignments(teacherName);
//                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}
    //Main loop

