package service;

import pojo.User;

/**
 * 类<code>UserService</code>用于:用户后端服务相关类
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-13
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
