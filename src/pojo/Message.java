package pojo;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-15
 */
public class Message {
    private String type; //消息类型，暂时为accepted(通过/拒绝)(这个直接用boolean展示),join(加入别人),ask(别人申请),recommend(建议)
    private String title; //文章标题(当消息类型为join或ask时可用)
    private String nickname; //昵称(当消息类型为join或ask时可用)
    private boolean iscomment; //是否评论(当消息类型为recommend时可用)
    private boolean isaccepted; //同意或拒绝
    private boolean isread; //是否读过

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

    public boolean isIscomment() {
        return iscomment;
    }

    public void setIscomment(boolean iscomment) {
        this.iscomment = iscomment;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public Message() {
    }

    public Message(String type,String title,String nickname, boolean iscomment,boolean isread,boolean isaccepted) {
        this.type = type;
        this.title = title;
        this.nickname = nickname;
        this.iscomment = iscomment;
        this.isread = isread;
        this.isaccepted = isaccepted;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", iscomment='" + iscomment + '\'' +
                ", isread=" + isread + '\'' +
                ", isaccepted" + isaccepted + '\'' +
                '}';
    }
}
