package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;
import pojo.User;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

public class test {
    @Test
    public void test(){
        List<Map<String, Object>> list = null;
        User user = new User();
        try {
            MongoDao mongoDao = new MongoDaoImpl();
            MongoDatabase db = MongoHelper.getMongoDataBase();
            BasicDBObject studentNoObj = new BasicDBObject("username", "uiiu");
            String table = "user";
            list = mongoDao.queryByDoc(db, table, studentNoObj);
            if(list.size()==1){
                Map<String,Object> map = list.get(0);
                String json = new Gson().toJson(map);
                user = new Gson().fromJson(json,User.class);
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
    public void testqueryArticles(){
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
            System.out.println("小组id"+groupidList);

            for (Integer integer : groupidList) {
                BasicDBObject groupidObj = new BasicDBObject("groupid", integer);
                String table2 = "article";
                List<Map<String, Object>> templist = mongoDao.queryByDoc(db, table2, groupidObj);
                articlelist.addAll(templist);
            }
            System.out.println("文章"+articlelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        res = new Gson().toJson(articlelist);
        System.out.println(res);
    }
}
