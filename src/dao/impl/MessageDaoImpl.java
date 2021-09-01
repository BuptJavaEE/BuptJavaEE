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
        List<Map<String, Object>> list = new ArrayList<>();
        List<Message> reslist = new ArrayList<>();
        Message tempmessage = new Message();
        try {
            Message message = new Message();
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject usernameObj = new BasicDBObject("username", username);
            String table = "message";
            list = mongoDao.queryByDoc(db, table, usernameObj);
            for (Map<String, Object> temp : list) {
                String json = new Gson().toJson(temp);
                tempmessage = new Gson().fromJson(json, Message.class);
                reslist.add(tempmessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslist;
    }

    @Override
    public void saveMessage(Message message) {
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            String table = "message";
            String json = new Gson().toJson(message);
            Document document = Document.parse(json);
            mongoDao.insert(db, table, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("插入成功！");
    }

    @Override
    public void deleteMessage(String username) {
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "message";
        BasicDBObject isreadObj = new BasicDBObject("isread",true);
        BasicDBObject usernameObj = new BasicDBObject("username",username);
        BasicDBObject andObj = new BasicDBObject("$and", Arrays.asList(isreadObj,usernameObj));
        try {
            mongoDao.delete(db,table,andObj);
            System.out.println("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
