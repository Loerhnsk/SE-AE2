package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Approval implements Serializable {
    private static final long serialVersionUID = 1L; // 为了确保序列化兼容


    private int isApproved; // 审批状态，0为未处理，为批准，2为否决
    private String approver; // 审批人
    private LocalDateTime approvalTime; // 审批时间
    private String comments; // 审批意见
    private int ID;//审批ID
    private int requirementID;//对应需求的ID
    

    // 构造器
    public Approval(int isApproved, String approver, LocalDateTime approvalTime, String comments) {
        this.isApproved = isApproved;
        this.approver = approver;
        this.approvalTime = approvalTime;
        this.comments = comments;
    }

    // isApproved的getter
    public int isApproved() {
        return isApproved;
    }

    // isApproved的setter
    public void setApproved(int approved) {
        isApproved = approved;
    }

    // approver的getter
    public String getApprover() {
        return approver;
    }

    // approver的setter
    public void setApprover(String approver) {
        this.approver = approver;
    }

    // approvalTime的getter
    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    // approvalTime的setter
    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }

    // comments的getter
    public String getComments() {
        return comments;
    }

    // comments的setter
    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public int getrID(){
        return requirementID;
    }

    public void setrID(int Id){
        this.requirementID = Id;
    }


    
    // 重写toString方法以便更容易地打印和查看Approval对象的信息
    @Override
    public String toString() {
        return "Approval{" +
                "isApproved=" + isApproved +
                ", approver='" + approver + '\'' +
                ", approvalTime=" + approvalTime +
                ", comments='" + comments + '\'' +
                '}';
    }
}
