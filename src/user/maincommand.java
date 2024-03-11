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
        while (true) {
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
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Main loop

}
