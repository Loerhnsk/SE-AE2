package Entity;

public class AssignedRequirement {

    private String directorName = "Jason Adams";
    ; // 教导主任名称
    private int requestId; // 申请ID

    private String className;//对应的课程名称
    private int TId;
    private String tname;//

    public AssignedRequirement() {

    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTId() {
        return TId;
    }

    public void setTId(int TId) {
        this.TId = TId;
    }

    public String getTName() {
        return tname;
    }

    public void setTName(String name) {
        this.tname = name;
    }

    public String toString() {
        return String.format("%-15s %-40s %-40s %-40s %-30s",
                "directorName:" + directorName,
                "requestId:" + requestId,
                "className:" + className,
                "TId:" + TId,
                "teaher name:" + tname);
    }

}
