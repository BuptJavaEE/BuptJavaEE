package pojo;

/**
 * 类<code>User</code>用于:实现用户类的相关操作及其需要的相关函数和构造函数
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-13
 */
public class User {
    private int id; //默认id
    private String username; //用户名
    private String password; //密码
    private String nickname;//昵称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User() {
    }

    public User(int id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
