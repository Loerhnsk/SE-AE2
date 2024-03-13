package user;

import Logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main command-line interface for interacting with different functionalities.
 */
public class maincommand {
    public static void main(String[] args) {
        // Initialize BufferedReader for user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Initialize instances of different functionalities
        Director director = new Director();
        Training training = new Training();
        CourseDirector courseDirector = new CourseDirector();
        Registration registration = new Registration();
        Search search = new Search();

        // Main command loop
        while (true) {
            try {
                // Output current state and prompt for command
                System.out.print("Command:");
                String userInput = reader.readLine();

                // Check user input for various commands
                if (userInput.equals("exit")) {
                    // Exit the program
                    break;
                }
                if (userInput.equals("Training")) {
                    // Trigger training functionality
                    training.training();
                }
                if (userInput.equals("Director")) {
                    // Trigger director functionality
                    director.director();
                }
                if (userInput.equals("Registration")) {
                    // Trigger registration functionality
                    registration.registration();
                }
                if (userInput.equals("CourseDirector")) {
                    // Trigger course director functionality
                    courseDirector.courseDirector();
                }
                if (userInput.equals("Search")) {
                    // Trigger search functionality
                    search.search();
                }
            } catch (IOException e) {
                // Handle IOException
                e.printStackTrace();
            }
        }
    }
    // Main loop
}
