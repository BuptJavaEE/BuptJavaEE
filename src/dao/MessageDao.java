package dao;

import pojo.Message;

import java.util.List;

/**
 * 类<code>MessageDao</code>用于:定义消息提醒类相关操作的一系列函数
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-15
 */
public interface MessageDao {
    /**
     * 根据用户名查找用户相关消息
     * @param username
     * @return
     */
    public List<Message> queryMessageByUsername(String username);

    /**
     * 保存消息
     * @param message
     */
    public void saveMessage(Message message);

    /**
     * 删除消息(已读的消息直接删除)
     * @param username
     */
    public void deleteMessage(String username,String date);
}
