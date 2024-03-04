package Logic;

import Entity.TeachingRequirement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BasicCommands {



    //输入一个教学需求的List和对应存储的json，将List里面的教学需求写入JSON中
    public static void writeTeachingRequirementsToJsonFile(List<TeachingRequirement> teachingRequirements, String filePath) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < teachingRequirements.size(); i++) {
            TeachingRequirement requirement = teachingRequirements.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append(String.format("    \"className\": \"%s\",\n", requirement.getClassName()));
            jsonBuilder.append(String.format("    \"directorName\": \"%s\",\n", requirement.getDirectorName()));
            jsonBuilder.append(String.format("    \"requestId\": %d,\n", requirement.getRequestId()));
            jsonBuilder.append(String.format("    \"requirement\": \"%s\",\n", requirement.getRequirement()));
            jsonBuilder.append(String.format("    \"teachingTime\": \"%s\",\n", requirement.getTeachingTime()));
            jsonBuilder.append(String.format("    \"requestStatus\": \"%s\"\n", requirement.getRequestStatus()));
            jsonBuilder.append("  }");
            if (i < teachingRequirements.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        jsonBuilder.append("]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //这里是测试用的
    // public static void main(String[] args) {
    //     List<TeachingRequirement> teachingRequirements = List.of(
    //         new TeachingRequirement("Jesus","John Weak", 1, "Mathematics", "semester 1", "pending")
    //         // 确保TeachingRequirement类构造器和方法与此调用相匹配
    //     );
    //     String filePath = "src/conf/Teaching_Requirement.json";
    //     writeTeachingRequirementsToJsonFile(teachingRequirements, filePath);
    }
}
