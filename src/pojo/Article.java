package pojo;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-18
 */
public class Article {
    private String textname; //文章标题
    private String text; //文章内容
    private int textno; //文章编号
    private int groupid; //小组编号

    public String getTextname() {
        return textname;
    }

    public void setTextname(String textname) {
        this.textname = textname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextno() {
        return textno;
    }

    public void setTextno(int textno) {
        this.textno = textno;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public Article() {
    }

    public Article(String textname, String text, int textno, int groupid) {
        this.textname = textname;
        this.text = text;
        this.textno = textno;
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "Article{" +
                "textname='" + textname + '\'' +
                ", text='" + text + '\'' +
                ", textno=" + textno +
                ", groupid=" + groupid +
                '}';
    }
}
