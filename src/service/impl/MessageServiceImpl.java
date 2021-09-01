package service.impl;

import com.google.gson.Gson;
import dao.MessageDao;
import dao.impl.MessageDaoImpl;
import pojo.Message;
import service.MessageService;

import java.util.List;

/**
 * 类<code>MessageServiceImpl</code>用于:消息后端处理相关类的函数实现
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-16
 */
public class MessageServiceImpl implements MessageService {

    @Override
    public String loadMessage(String username) {
        MessageDao messageDao = new MessageDaoImpl();
        List<Message> list = messageDao.queryMessageByUsername(username);
        return new Gson().toJson(list);
    }

    @Override
    public void removeMessage(String username) {
        MessageDao messageDao = new MessageDaoImpl();
        messageDao.deleteMessage(username);
    }
}
