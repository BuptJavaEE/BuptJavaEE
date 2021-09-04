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
    private String text; //没屁用
    private String textno; //文章编号
    private String groupid; //小组编号
    private String content; //文章内容
    private int browsertimes; //浏览次数
    private int commentCount; //评论次数
    private double averagepoint; //平均分

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

    public String getTextno() {
        return textno;
    }

    public void setTextno(String textno) {
        this.textno = textno;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public int getBrowsertimes() {
        return browsertimes;
    }

    public void setBrowsertimes(int browsertimes) {
        this.browsertimes = browsertimes;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getAveragepoint() {
        return averagepoint;
    }

    public void setAveragepoint(double averagepoint) {
        this.averagepoint = averagepoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article() {
    }

    public Article(String textname, String text, String textno, String groupid, String content,int browsertimes, int commentCount, double averagepoint) {
        this.textname = textname;
        this.text = text;
        this.textno = textno;
        this.groupid = groupid;
        this.content = content;
        this.browsertimes = browsertimes;
        this.commentCount = commentCount;
        this.averagepoint = averagepoint;
    }

    @Override
    public String toString() {
        return "Article{" +
                "textname='" + textname + '\'' +
                ", text='" + text + '\'' +
                ", textno=" + textno +
                ", groupid=" + groupid +
                ", browsertimes=" + browsertimes +
                '}';
    }
}
