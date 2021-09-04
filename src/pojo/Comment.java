package pojo;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-09
 */
public class Comment {
    private int id; //没有的狗东西
    private String textno; //文章编号
    private String username; //评论人的名称
    private String content; //评论内容
    private String title; //文章标题
    private String context; //不知道干啥用
    private int point; //打分分数
    private String date; //评论发表日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextno() {
        return textno;
    }

    public void setTextno(String textno) {
        this.textno = textno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Comment() {
    }

    public Comment(int id, String textno, String username, String content, String title, String context, int point,String date) {
        this.id = id;
        this.textno = textno;
        this.username = username;
        this.content = content;
        this.title = title;
        this.context = context;
        this.point = point;
        this.date =date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textno='" + textno + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", point=" + point +
                '}';
    }
}
