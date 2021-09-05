package dao.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.MessageDao;
import org.bson.Document;
import pojo.Message;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Map<String, Object>> list1 = new ArrayList<>();//存储查询结果用的表
        List<Message> reslist = new ArrayList<>();//结果表
        Message tempmessage = new Message();//临时存储
        Message tempmessage1 = new Message();
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        BasicDBObject usernameObj = new BasicDBObject("username", username);
        BasicDBObject towhoObj = new BasicDBObject("towho", username);
        String table = "message";
        try {
            list = mongoDao.queryByDoc(db, table, usernameObj);
            list1 = mongoDao.queryByDoc(db, table, towhoObj);
            for (Map<String, Object> map : list) {
                String Json = new Gson().toJson(map);
                tempmessage = new Gson().fromJson(Json, Message.class);
                System.out.println("tempmessage:" + tempmessage);
                reslist.add(tempmessage);
            }
            for (Map<String, Object> stringObjectMap : list1) {
                String Json = new Gson().toJson(stringObjectMap);
                tempmessage1 = new Gson().fromJson(Json, Message.class);
                System.out.println("tempmessage1: " + tempmessage1);
                reslist.add(tempmessage1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslist;
    }

    @Override
    public void saveMessage(Message message) {
        List<Map<String, Object>> updatelist;
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "message";
        String Json = new Gson().toJson(message);
        Document document = Document.parse(Json);
        try {
            if (mongoDao.insert(db, table, document))
                System.out.println("插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(String username, String date) {
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "message";
        BasicDBObject usernameObj = new BasicDBObject("username", username).append("date", date);
        BasicDBObject towhoObj = new BasicDBObject("towho",username).append("date",date);
        try {
            if (mongoDao.delete(db, table, usernameObj) || mongoDao.delete(db,table,towhoObj))
                System.out.println("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
