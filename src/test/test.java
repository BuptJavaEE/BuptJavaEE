package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import dao.CommentDao;
import dao.impl.ArticleDaoImpl;
import dao.impl.CommentDaoImpl;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import pojo.Article;
import pojo.Message;
import pojo.User;
import utils.JsonStrToMap;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    public void test3() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(date.getTime());
        System.out.println(date);
        System.out.println(times);
    }

    @Test
    public void test4() {
        List<Article> list = new ArticleDaoImpl().queryAllArticles();
        System.out.println(list.get(0).getBrowsertimes());
        System.out.println(list);
    }

    @Test
    public void test5() {
        CommentDao commentDao = new CommentDaoImpl();
        int allpoints = commentDao.getAllpoints("1");
        int count = commentDao.getCommentCount("1");
        System.out.println(allpoints);
        System.out.println(count);
        int averpoints = allpoints / count;
    }

    @Test
    public void test6() {
        MongoDatabase db = MongoHelper.getMongoDataBase();
        MongoDao mongoDao = new MongoDaoImpl();
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        int id = 0;
        String resStr = null;
        String groupid = null;
        int groupleader = 0;
        String table = "user";
        String table1 = "group";
        String table2 = "article";
        BasicDBObject userNameObj = new BasicDBObject("username", "abcdefg");
        BasicDBObject textnoObj = new BasicDBObject("textno", "1");
        try {
            list = mongoDao.queryByDoc(db, table, userNameObj);
            for (int i = 0; i < list.size(); i++) {
                id = Integer.parseInt(list.get(0).get("id").toString());
            }
            System.out.println(list);
            list1 = mongoDao.queryByDoc(db, table2, textnoObj);
            System.out.println(list1);
            for (int j = 0; j < list1.size(); j++) {
                groupid = list1.get(0).get("groupid").toString();
            }
            BasicDBObject groupidObj = new BasicDBObject("groupid", groupid);
            list2 = mongoDao.queryByDoc(db, table1, groupidObj);
            for (int k = 0; k < list2.size(); k++) {
                groupleader = Integer.parseInt(list2.get(0).get("groupleader").toString());
            }
            BasicDBObject userIdObj = new BasicDBObject("id", id);
            if (mongoDao.queryByDoc(db, table1, userIdObj) == null) {
                //没有用户就插入
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("groupid", groupid);
                jsonObject.addProperty("groupleader", groupleader);
                jsonObject.addProperty("id", id);
                String json = new Gson().toJson(jsonObject);
                Document document = Document.parse(json);
                mongoDao.insert(db, table1, document);
                resStr = "申请成功！";
            } else {
                //有的话就返回
                resStr = "您已经在这个小组了！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test7(){
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject basicDBObject = new BasicDBObject("id",25033);
        MongoDao mongoDao = new MongoDaoImpl();
        try {
            System.out.println(mongoDao.queryByDoc(db,table,basicDBObject).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test8(){
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "comment";
        BasicDBObject usernameObj = new BasicDBObject("username","初号机");
        BasicDBObject resObj = usernameObj.append("textno","1");
        MongoCollection<Document> collection = db.getCollection(table);
        DeleteResult deleteManyResult = collection.deleteMany(resObj);
        deleteManyResult.getDeletedCount();
    }
    @Test
    public void test9(){
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject userIdObj = new BasicDBObject("id",3082);
        BasicDBObject grouIdObj = new BasicDBObject("groupid","1");
        BasicDBObject andObj = new BasicDBObject("$and", Arrays.asList(userIdObj,grouIdObj));
        MongoDaoImpl mongoDao = new MongoDaoImpl();
        try {
            System.out.println(mongoDao.queryByDoc(db,table,andObj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
