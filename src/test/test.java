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
}
