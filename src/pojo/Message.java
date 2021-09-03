package pojo;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-15
 */
public class Message {
    private String type; //消息类型，暂时为pass/refuse(通过/拒绝),writing(加入别人),apply(别人申请),suggest(建议)
    private String title; //文章标题(当消息类型为writing或apply时可用)
    private String nickname; //昵称(当消息类型为writing或apply时可用)
    private String username; //消息所有者的用户名
    private String textno; //文章号

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextno() {
        return textno;
    }

    public void setTextno(String textno) {
        this.textno = textno;
    }

    public Message() {
    }

    public Message(String type, String title, String nickname, String username, String textno) {
        this.type = type;
        this.title = title;
        this.nickname = nickname;
        this.username = username;
        this.textno = textno;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", textno=" + textno +
                '}';
    }
}
