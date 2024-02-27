package Logic;

import Entity.*;

import java.time.LocalDateTime;
import java.util.*;

public class BasicCommands {

    // 提交教学需求到审批申请里面
    public static void summitRequirement(LocalDateTime time, String comments, List<Approval> approvalList,TeachingRequirement requirement) {
        Approval approval = new Approval(0, null, time, comments);// 这里把审批人设空，等到正式批审的时候再进行署名
        approval.setrID(requirement.getID());//把审批的rID和对应的教学需求ID对应起来
        approvalList.add(approval);//把申请添加到审批列表中
    }

    //创建对应的教学需求
    public static TeachingRequirement createRequirement(Course course, String time,int teacher){
        TeachingRequirement requirement = new TeachingRequirement(course, teacher, time);
        return requirement;
    }


}
