package user;


import Logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class maincommand {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 创建 Director 实例并注入数据读取实现类
        DataReader dataReader = new TxtFileDataReader();
        DataWriter dataWriter = new TxtFileDataWriter();
        Database database = new Database(dataReader, dataWriter);

        Director director = new Director(database);
        Training training = new Training(database);
        Registration registration = new Registration(database);

        Search search = new Search();

while(true){
            try {
                //Output current state
                System.out.print("Command:");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    break;
                }
                if (userInput.equals("Training")) {
                    training.training();
                }
                if(userInput.equals("Director"))
                {
                    director.director();
                }
                if (userInput.equals("Registration")){
                    registration.registration();
                }
                if (userInput.equals("CourseDirector")){
                    CourseDirector.courseDirector();
                }
                if (userInput.equals("Search")) {
                    search.search();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}
    //Main loop

