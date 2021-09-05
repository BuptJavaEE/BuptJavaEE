package pojo;

import org.bson.types.ObjectId;

import java.util.Date;

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
    private String towho; //发给谁
    private String standardDate; //标准时间
    private String date; //CST表示时间

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

    public String getStandardDate() {
        return standardDate;
    }

    public void setStandardDate(String standardDate) {
        this.standardDate = standardDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTowho() {
        return towho;
    }

    public void setTowho(String towho) {
        this.towho = towho;
    }

    public Message() {
    }

    public Message(String type, String title, String nickname, String username, String textno, String towho,String standardDate,String date) {
        this.type = type;
        this.title = title;
        this.nickname = nickname;
        this.username = username;
        this.textno = textno;
        this.towho = towho;
        this.standardDate = standardDate;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", textno='" + textno + '\'' +
                ", towho='" + textno + '\'' +
                ", standardDate='" + standardDate + '\'' +
                ", date=" + date +
                '}';
    }
}
