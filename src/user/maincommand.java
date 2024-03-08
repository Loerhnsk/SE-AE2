package user;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class maincommand {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
                    Director.director();
                }
                if (userInput.equals("Registeration")){
                    Registration.registration();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Main loop

}
