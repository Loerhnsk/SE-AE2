package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class maincommand {
    public static void main(String[] args) {
        // Initialize BufferedReader for user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Initialize instances of various classes
        Director director = new Director();
        Training training = new Training();
        CourseDirector courseDirector = new CourseDirector();
        Search search = new Search();

        // Main command processing loop
        while (true) {
            try {
                // Prompt user for input
                System.out.print("Command:");
                String userInput = reader.readLine();

                // Check user input for specific commands
                if (userInput.equals("exit")) {
                    // Exit the loop if the user inputs "exit"
                    break;
                }
                if (userInput.equals("Training")) {
                    // Execute training functionality if the user inputs "Training"
                    training.training();
                }
                if (userInput.equals("Director")) {
                    // Execute director functionality if the user inputs "Director"
                    director.director();
                }
                if (userInput.equals("Registration")) {
                    // Execute registration functionality if the user inputs "Registration"
                    Registration.registration();
                }
                if (userInput.equals("CourseDirector")) {
                    // Execute course director functionality if the user inputs "CourseDirector"
                    courseDirector.courseDirector();
                }
                if (userInput.equals("Search")) {
                    // Execute search functionality if the user inputs "Search"
                    search.search();
                }
            } catch (IOException e) {
                // Print the stack trace in case of an IOException
                e.printStackTrace();
            }
        }
    }
}
// Main loop for processing user commands
