package user;

import Logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Director {
    private Database database;
    // 构造函数接受 DataReader 实例
    public Director() {

    }

    public void director() {
        // Read from file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataReader dataReader = new TxtFileDataReader();
        DataWriter dataWriter = new TxtFileDataWriter();
         database = new Database(dataReader, dataWriter);
        // Output current state
        while (true) {
            try {
                System.out.println("Pending request:");
                BasicCommands.outputrequirement(database.getDataReader().getTeachingRequirements());
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                BasicCommands.outputTeacher(database.getDataReader().getTeachers());
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");

                // Read User Input from terminal
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    // Write current data to the file

                   database.getDataWriter().writeTeachingRequirements(database.getDataReader().getTeachingRequirements(), database.getDataReader().getRequestFilePath());
                   database.getDataWriter().writeTeachers(database.getDataReader().getTeachers(), database.getDataReader().getTeacherFilePath());
                   database.getDataWriter().writeAssignedRequirements(database.getDataReader().getAssignedRequirements(), database.getDataReader().getAssignedFilePath());
                    break;
                }

                // Input Order checking
                String[] Order = userInput.split(",");

                if (Order.length != 2)
                    System.out.println("Wrong Order");
                else if (Order[0].equals("reject"))
                    BasicCommands.Rejecting(database.getDataReader().getTeachingRequirements(), Integer.parseInt(Order[1]));
                else
                    BasicCommands.Approvalrequest(database.getDataReader().getTeachingRequirements(),
                            database.getDataReader().getTeachers(),
                            database.getDataReader().getAssignedRequirements(),
                            Integer.parseInt(Order[0]), Integer.parseInt(Order[1]));

                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
