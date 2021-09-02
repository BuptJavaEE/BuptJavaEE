package dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.UserDao;
import org.bson.Document;
import pojo.User;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 类<code>UserDaoImpl</code>用于:实现UserDao类接口
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-13
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        List<Map<String, Object>> list = new ArrayList<>();
        User user = null;
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject usernameObj = new BasicDBObject("username", username);
            String table = "user";
            list = mongoDao.queryByDoc(db, table, usernameObj);
            if (list.size() == 1) {
                Map<String, Object> map = list.get(0);
                String json = new Gson().toJson(map);
                user = new Gson().fromJson(json, User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        List<Map<String, Object>> list = null;
        User user = null;
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject usernameObj = new BasicDBObject("username", username);
            BasicDBObject pwdObj = new BasicDBObject("password", password);
            BasicDBObject andObj = new BasicDBObject("$and", Arrays.asList(usernameObj, pwdObj));
            String table = "user";
            list = mongoDao.queryByDoc(db, table, andObj);
            if (list.size() == 1) {
                Map<String, Object> map = list.get(0);
                String json = new Gson().toJson(map);
                user = new Gson().fromJson(json, User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int saveUser(User user) {
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            String table = "user";
            String json = new Gson().toJson(user);
            Document document = Document.parse(json);
            mongoDao.insert(db, table, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("插入成功！");
        return 1;
    }

    @Override
    public boolean queryUserId(int id) {
        List<Map<String, Object>> list = null;
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject idObj = new BasicDBObject("id", id);
            String table = "user";
            list = mongoDao.queryByDoc(db, table, idObj);
            if (list.size() > 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User queryUserByUserId(int id) {
        return null;
    }
}
