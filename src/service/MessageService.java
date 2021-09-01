package service;

/**
 * 类<code>MessageService</code>用于:消息后端服务相关类
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-16
 */
public interface MessageService {
    public String loadMessage(String username);
    public void removeMessage(String username);
}
