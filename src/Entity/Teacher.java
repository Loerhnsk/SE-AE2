package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容
    
    private String id;
    private String name;
    private List<String> skills;
    private List<String> trainings;

    // 构造器
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>();
        this.trainings = new ArrayList<>();
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public void addSkill(String skill) {
        this.skills.add(skill);
    }
    public List<String> getTrainings() {
        return trainings;
    }
    public void setTrainings(List<String> trainings) {
        this.trainings = trainings;
    }
    public void addTraining(String training) {
        this.trainings.add(training);
    }

    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                ", trainings=" + trainings +
                '}';
    }
}
