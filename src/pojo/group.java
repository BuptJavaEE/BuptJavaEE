package pojo;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-19
 */
public class group {
    private int groupid; //小组号
    private int id; //组员号
    private int groupleader; //组长号

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupleader() {
        return groupleader;
    }

    public void setGroupleader(int groupleader) {
        this.groupleader = groupleader;
    }

    public group() {
    }

    public group(int groupid, int id, int groupleader) {
        this.groupid = groupid;
        this.id = id;
        this.groupleader = groupleader;
    }

    @Override
    public String toString() {
        return "group{" +
                "groupid=" + groupid +
                ", id=" + id +
                ", groupleader=" + groupleader +
                '}';
    }
}
