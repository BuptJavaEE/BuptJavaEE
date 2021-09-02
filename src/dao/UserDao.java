package dao;

import pojo.User;

import java.util.List;

/**
 * 类<code>UserDao</code>用于:定义User类相关操作所需要的一系列基础函数
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-13
 */
public interface UserDao {

    /**
     *
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 如果返回1,说明保存成功,反之亦然
     */
    public int saveUser(User user);

    /**
     * 查找用户id,查重
     * @param id
     * @return
     */
    public boolean queryUserId(int id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User queryUserByUserId(int id);
}
