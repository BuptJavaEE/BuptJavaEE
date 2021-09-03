package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import pojo.Message;
import pojo.User;
import utils.JsonStrToMap;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    public void test() {
        List<Map<String, Object>> list = null;
        User user = new User();
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject studentNoObj = new BasicDBObject("username", "uiiu");
            String table = "user";
            list = mongoDao.queryByDoc(db, table, studentNoObj);
            if (list.size() == 1) {
                Map<String, Object> map = list.get(0);
                String json = new Gson().toJson(map);
                user = new Gson().fromJson(json, User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getNickname());
    }

    @Test
    public void testqueryArticles() {
        List<Map<String, Object>> grouplist; //小组列表
        List<Map<String, Object>> articlelist = new ArrayList<>(); //文章列表
        List<Integer> groupidList = new ArrayList<>(); //id列表
        String res;
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject idObj = new BasicDBObject("id", 1);
        try {
            grouplist = mongoDao.queryByDoc(db, table, idObj);

            for (Map<String, Object> stringObjectMap : grouplist) {
                int temp = Integer.parseInt(stringObjectMap.get("groupid").toString());
                groupidList.add(temp);
            }
            System.out.println("小组id" + groupidList);

            for (Integer integer : groupidList) {
                BasicDBObject groupidObj = new BasicDBObject("groupid", integer);
                String table2 = "article";
                List<Map<String, Object>> templist = mongoDao.queryByDoc(db, table2, groupidObj);
                articlelist.addAll(templist);
            }
            System.out.println("文章" + articlelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        res = new Gson().toJson(articlelist);
        System.out.println(res);
    }

    @Test
    public void printMessage() {
        List<Map<String, Object>> list = new ArrayList<>();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "comment";
        MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find();
        for (Document user : iterable) {
            String jsonString = user.toJson();
            Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
            list.add(jsonStrToMap);
        }
        if (list.size() == 1) {
            String json = list.get(0).get("_id").toString();
            System.out.println(json);
            long time = Integer.parseInt((json.toString()).substring(0, 8), 16) * 1000L;
            System.out.println(time);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(time);
            String times = format.format(date.getTime());
            System.out.println(date);
            System.out.println(times);
        }
    }

    @Test
    public void test2() {
        List<Map<String, Object>> list = new ArrayList<>();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "comment";
        MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find();
        for (Document user : iterable) {
            String jsonString = user.toJson();
            Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
            list.add(jsonStrToMap);
        }
        if (list.size() == 1) {
            String json = new Gson().toJson(list.get(0).get("_id"));
            System.out.println(json);
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            long timestamp = jsonObject.get("timestamp").getAsLong() * 1000L;
            //long time = Integer.parseInt((json.toString()).substring(0, 8), 16) * 1000L;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(timestamp);
            String times = format.format(date.getTime());
            System.out.println(date);
            System.out.println(times);
        }
    }
}
