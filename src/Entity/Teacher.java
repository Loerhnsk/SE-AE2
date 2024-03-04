package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容
    
    private int id;
    private String name;
    private List<String> skills;
    private boolean approve;
    private String train;

    // 构造器
    public Teacher(String name, int id, List<String>skills, boolean approve, String train) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>(skills);
        this.approve = approve;
        this.train =train;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public boolean checkSkill(String skill) {
        for (String s : skills) {
            if(s.equals(skill))return true;
        }
        return false;
    }
    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public boolean checkAssign(){return this.approve;}
    public void setAssign(){this.approve = true;}
    public String getTrain(){
        if(this.train == null)return "";
        return this.train;
    }
    public void setTrain(String train){this.train = train;}



    public String toString() {
        String output = String.format("%-15s %-40s", "Id:" + id, "name:" + name);
        output = output + "Skills: ";
        for (String skill : skills) {
            output = output + skill + "; ";
        }
        return output;
    }
}
