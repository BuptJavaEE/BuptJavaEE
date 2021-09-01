package dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.MessageDao;
import org.bson.Document;
import pojo.Message;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 类<code>MessageDaoImpl</code>用于:实现消息提醒类相关操作的一系列函数
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-15
 */
public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> queryMessageByUsername(String username) {
        List<Map<String, Object>> list = new ArrayList<>();//存储查询结果用的表
        List<Message> reslist = new ArrayList<>();//结果表
        Message tempmessage = new Message();//临时存储
        /**
         * 数据库相关操作
         */
        return reslist;
    }

    @Override
    public void saveMessage(Message message) {
        /**
         * 数据库相关操作
         */
        System.out.println("插入成功！");
    }

    @Override
    public void deleteMessage(String username) {
        /**
         * 数据库相关操作
         */
        System.out.println("删除成功！");
    }
}
