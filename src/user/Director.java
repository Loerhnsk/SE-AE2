package user;

import Logic.BasicCommands;
import Logic.DataReader;
import Logic.TxtFileDataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Director {
    private DataReader dataReader;

    // 构造函数接受 DataReader 实例
    public Director(DataReader dataReader) {
        this.dataReader = new TxtFileDataReader();
    }

    public void director() {
        // Read from file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Output current state
        while (true) {
            try {
                System.out.println("Pending request:");
                BasicCommands.outputrequirement(dataReader.readTeachingRequirements());
                BasicCommands.writeline();
                System.out.println("Available Teacher");
                BasicCommands.outputTeacher(dataReader.readTeachers());
                BasicCommands.writeline();
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");

                // Read User Input from terminal
                String userInput = reader.readLine();

                if (userInput.equals("exit")) {
                    // Write current data to the file
                    BasicCommands.writeTeachingRequirementsToTxtFile(dataReader.readTeachingRequirements(), dataReader.getRequestFilePath());
                    BasicCommands.writeTeacherToTxtFile(dataReader.readTeachers(), dataReader.getTeacherFilePath());
                    BasicCommands.writeAssignedRequirementsToTxtFile(dataReader.readAssignedRequirements(), dataReader.getAssignedFilePath());
                    break;
                }

                // Input Order checking
                String[] Order = userInput.split(",");

                if (Order.length != 2)
                    System.out.println("Wrong Order");
                else if (Order[0].equals("reject"))
                    BasicCommands.Rejecting(dataReader.readTeachingRequirements(), Integer.parseInt(Order[1]));
                else
                    BasicCommands.Approvalrequest(dataReader.readTeachingRequirements(),
                            dataReader.readTeachers(),
                            dataReader.readAssignedRequirements(),
                            Integer.parseInt(Order[0]), Integer.parseInt(Order[1]));

                BasicCommands.writeline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
