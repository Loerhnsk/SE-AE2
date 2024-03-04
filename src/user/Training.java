package user;

import java.io.*;
import java.util.List;

import Entity.Teacher;
import Entity.TeachingRequirement;

import Logic.BasicCommands;

public class Training {
    private static boolean checkID(String line, String ID){
        String[] parts = line.split(",",-1);
        if (parts.length == 6) {
            return parts[2].equals(ID);
        } else return parts[1].equals(ID);

    }
    private static boolean checkPending(String line){
        String[] parts = line.split(",",-1);
        if (parts.length == 6)
            return parts[5].equals("pending");
        return false;
    }
    private static boolean checkAssigned(String line){
        String[] parts = line.split(",",-1);
        if (parts.length == 5)
            return parts[3].equals("true");
        return false;
    }
    private static boolean checkskill(String line, String skill){
        String[] parts = line.split(",",-1);
        if (parts.length == 5){
            String[] skills = parts[2].split("/");
            for (String s : skills) {
                if(s.equals(skill))return true;
            }
        }
        return false;
    }
    private static String getrequirement(String line){
        String[] parts = line.split(",",-1);
        if (parts.length == 6)
            return parts[3];
        else return "";
    }
    private static String trans(String input){
        String[] parts = input.split(",",-1);
        String output ="";
        if (parts.length == 6) {
            if(!checkPending(input))return null;
            output = String.format("%-15s %-40s %-40s %-40s %-30s","requestId:" + parts[2], "className:" + parts[0] , "directorName:" + parts[1], "requirement:" + parts[3],"teachingTime:" + parts[4]);

        } else if(parts.length == 5){
            if(checkAssigned(input))return null;
            String[] skills = parts[2].split("/");
            output = String.format("%-15s %-40s" ,"Id:" + parts[1], "name:" + parts[0]);
            for (String skill : skills) {
                output = output + skill +";";
            }

        }
        else {
            output ="Invalid format: " + input; // 输出无效格式的行
        }
        return output;
    }
    private static void outputall(File file){
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 读取文件内容
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = trans(line);
                if(line != null)System.out.println(line);
            }
            // 关闭文件
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  static String Teach(String input,String skill){
        String[] parts = input.split(",",-1);
        parts[3] = "true";
        parts[4] = skill;
        return String.join(",",parts);
    }
    private  static String reject(String input){
        String[] parts = input.split(",",-1);
        parts[5] = "rejected";
        return String.join(",",parts);
    }

    private  static String approve(String input){
        String[] parts = input.split(",",-1);
        parts[5] = "approved";
        return String.join(",",parts);
    }

    private static void Training(File requestFile, File teacherFile, String requestID, String teacherID){
        String requirement = "";
        StringBuilder content_r = new StringBuilder();
        StringBuilder content_t = new StringBuilder();
        boolean isChanged = false;
        boolean Notfound = true;
        try {
            FileReader fileReader = new FileReader(requestFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 读取文件内容
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(checkID(line,requestID)) {
                    if (!checkPending(line)) {
                        System.out.println("Not Pending");
                        bufferedReader.close();
                        fileReader.close();
                        return;
                    } else {
                        requirement = getrequirement(line);
                        line = approve(line);
                        Notfound = false;
                    }
                }
                content_r.append(line).append("\n");
            }
            // 关闭文件
            bufferedReader.close();
            fileReader.close();
            fileReader = new FileReader(teacherFile);
            bufferedReader = new BufferedReader(fileReader);
            if (Notfound) {
                System.out.println("Request Not Found");
                bufferedReader.close();
                fileReader.close();
                return;
            }
            while ((line = bufferedReader.readLine()) != null) {
                if(checkID(line,teacherID)) {
                    if(checkAssigned(line)){
                        System.out.println("Already Assigned");
                        bufferedReader.close();
                        fileReader.close();
                        return;
                    }else {
                        if(checkskill(line,requirement)){
                            System.out.println("Already Learned");
                            bufferedReader.close();
                            fileReader.close();
                            return;
                        }else{
                            line = Teach(line,requirement);
                            isChanged = true;
                        }
                    }
                }
                content_t.append(line).append("\n");
            }
            // 关闭文件
            bufferedReader.close();
            fileReader.close();

            if(!isChanged){
                System.out.println("Teacher Not Found");
                return;
            }

            FileWriter fileWriter = new FileWriter(teacherFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content_t.toString());
            bufferedWriter.close();
            fileWriter.close();
            fileWriter = new FileWriter(requestFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content_r.toString());
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Trained");
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
    private static void Rejecting(File file, String ID){
        try {
            boolean isPending = false;
            boolean isChange = false;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 读取文件内容
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if(checkID(line,ID)) {
                    if (checkPending(line)) {
                        line = reject(line);
                        isChange = true;
                    } else isPending = true;
                }
                content.append(line).append("\n");
            }

            // 关闭文件
            bufferedReader.close();
            fileReader.close();

            // 将修改后的内容写回文件
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());
            bufferedWriter.close();
            fileWriter.close();
            if(isPending) System.out.println("Not Pending");
            else if(!isChange) System.out.println("Request Not found");
            else System.out.println("Rejected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File requestfile = new File("src\\conf\\Teaching_Requirement.txt");
        File teacherfile = new File("src\\conf\\teacher.txt");
        List<TeachingRequirement> teachingRequirements1 = BasicCommands.readTeachingRequirementsFromTxtFile("src\\conf\\Teaching_Requirement.txt");
        List<Teacher> teacher = BasicCommands.readTeacherFromTxtFile("src\\conf\\teacher.txt");
        for (TeachingRequirement tr : teachingRequirements1) {
            System.out.println(tr);
        }
        for (Teacher te : teacher) {
            System.out.println(te);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Pending request:");
                outputall(requestfile);
                System.out.println("Available Teacher");
                outputall(teacherfile);
                System.out.print("Enter request id and teacher id to match or Enter 'exit' to exit：");
                String userInput = reader.readLine();
                if (userInput.equals("exit")) {
                    break;
                }
                String[] Order = userInput.split(",");
                if(Order.length != 2) System.out.println("Wrong Order");
                else if(Order[0].equals("reject"))Rejecting(requestfile,Order[1]);
                else Training(requestfile,teacherfile,Order[0],Order[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
