package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtFileDataWriter implements DataWriter {
    @Override
    public void writeTeachingRequirements(List<TeachingRequirement> teachingRequirements, String filePath) {
        // 实现写入教学需求的逻辑
        StringBuilder txtBuilder = new StringBuilder();
        for (TeachingRequirement requirement : teachingRequirements) {
            txtBuilder.append(String.format("%s,%s,%d,%s,%s,%s\n",
                    requirement.getClassName(),
                    requirement.getDirectorName(),
                    requirement.getRequestId(),
                    requirement.getRequirement(),
                    requirement.getTeachingTime(),
                    requirement.getRequestStatus()));
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeTeachers(List<Teacher> teachers, String filePath) {
        // 实现写入教师信息的逻辑
        StringBuilder txtBuilder = new StringBuilder();
        for (Teacher teacher : teachers) {
            txtBuilder.append(String.format("%s,%d,",
                    teacher.getName(),
                    teacher.getId()));
            List<String> skills = teacher.getSkills();
            for (String skill : skills) {
                if (skill != null) txtBuilder.append(skill);
                txtBuilder.append("/");
            }
            txtBuilder.deleteCharAt(txtBuilder.length() - 1);
            if (teacher.checkAssign()) txtBuilder.append(",true,");
            else txtBuilder.append(",false,");
            txtBuilder.append(teacher.getTrain()).append("\n");
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeAssignedRequirements(List<AssignedRequirement> assignedRequirements, String filePath) {
        // 实现写入已分配需求的逻辑
        StringBuilder txtBuilder = new StringBuilder();
        for (AssignedRequirement requirement : assignedRequirements) {
            txtBuilder.append(String.format("%s,%d,%s,%d,%s\n",
                    requirement.getDirectorName(),
                    requirement.getRequestId(),
                    requirement.getClassName(),
                    requirement.getTId(),
                    requirement.getTName()));
        }

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(txtBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
