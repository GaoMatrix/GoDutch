package mobidever.godutch.model;

import java.util.Date;

/**
 * 实体类是对表的映射
 *
 *实体类最好把注释都写清楚，因为实体类是对表的映射，不写清楚注释，很容易高不清
 *这个字段代表什么意思。
 */
public class User {
    //用户主键id
    private int id;
    //用户名称
    private String name;
    //添加日期
    private Date createDate = new Date();
    //状态 不要弄成一个boolean值，如果只有删除还是没删除可以，但是如果出来一个新的状态，
    //boolean就不够用了
    //状态0失效1启用
    private int state = 1;
    
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
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
}
