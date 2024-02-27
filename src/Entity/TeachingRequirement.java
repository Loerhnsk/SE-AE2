package Entity;
import java.io.Serializable;

public class TeachingRequirement implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容

    private Course course;//需求对应的课程
    private int requiredNumberOfTeachers;//课程所需教师人数
    private String timePeriod;//上课时间段
    private int requirementID;//这个教学需求自己的ID

    // 构造器
    public TeachingRequirement(Course course, int requiredNumberOfTeachers, String timePeriod) {
        this.course = course;
        this.requiredNumberOfTeachers = requiredNumberOfTeachers;
        this.timePeriod = timePeriod;
    }

    // 课程的getter和setter
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // 所需教师数量的getter和setter
    public int getRequiredNumberOfTeachers() {
        return requiredNumberOfTeachers;
    }

    public void setRequiredNumberOfTeachers(int requiredNumberOfTeachers) {
        this.requiredNumberOfTeachers = requiredNumberOfTeachers;
    }

    // 时间段的getter和setter
    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }


    public int getID(){
        return requirementID;
    }

    public void setID(int Id){
        this.requirementID = Id;
    }



    // 重写toString方法以便更容易地打印和查看TeachingRequirement对象的信息
    @Override
    public String toString() {
        return "TeachingRequirement{" +
                "course=" + course +
                ", requiredNumberOfTeachers=" + requiredNumberOfTeachers +
                ", timePeriod='" + timePeriod + '\'' +
                '}';
    }
}
