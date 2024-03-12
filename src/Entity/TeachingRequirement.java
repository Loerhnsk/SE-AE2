package Entity;

import java.io.Serializable;

public class TeachingRequirement implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容

    private String className;//对应的课程名称
    private String directorName; // 教导主任名称
    private int requestId; // 申请ID
    private String requirement; // 需求内容
    private String teachingTime; // 教学时间
    private String requestStatus; // 申请状态
    private static int autoID;//自动生成的ID

    // 构造器
    public TeachingRequirement(String className, String directorName, int requestId, String requirement, String teachingTime, String requestStatus) {
        this.className = className;
        this.directorName = directorName;
        this.requestId = requestId;
        this.requirement = requirement;
        this.teachingTime = teachingTime;
        this.requestStatus = requestStatus;
    }

    //创建新的
    public TeachingRequirement(String className, String directorName, String requirement, String teachingTime) {
        this.className = className;
        this.directorName = directorName;
        this.requestId = autoID;
        autoID = autoID + 1;
        this.requirement = requirement;
        this.teachingTime = teachingTime;
        this.requestStatus = "pending";
    }


    // directorName的getter和setter
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    // requestId的getter和setter
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    // requirement的getter和setter
    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    // teachingTime的getter和setter
    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    // requestStatus的getter和setter
    public String getRequestStatus() {
        return requestStatus;
    }

    public boolean checkPending() {
        if (this.requestStatus.equals("pending")) return true;
        return false;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    // 重写toString方法以便更容易地打印和查看TeachingRequirement对象的信息
    @Override
    public String toString() {

        return String.format("%-15s %-40s %-40s %-40s %-30s %-30s",
                "requestId:" + requestId,
                "className:" + className,
                "directorName:" + directorName,
                "requirement:" + requirement,
                "teachingTime:" + teachingTime,
                "Status:" + requestStatus);


    }
}