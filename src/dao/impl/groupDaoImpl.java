package dao.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.groupDao;
import org.bson.Document;
import utils.MongoDao;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-19
 */
public class groupDaoImpl implements groupDao {
    @Override
    public String queryGroupByGroupId(String groupid) {
        List<Map<String, Object>> list = new ArrayList<>();
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject idObj = new BasicDBObject("groupid", groupid);
        try {
            list = mongoDao.queryByDoc(db, table, idObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(list);
    }

    @Override
    public List<Integer> queryAuthorsByGroupId(String groupid) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Integer> authorlist = new ArrayList<>();
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject leaderObj = new BasicDBObject("groupid", groupid);
        try {
            list = mongoDao.queryByDoc(db, table, leaderObj);
            for (Map<String, Object> stringObjectMap : list) {
                Integer id = Integer.valueOf(stringObjectMap.get("id").toString());
                authorlist.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorlist;
    }

    @Override
    public String AddMemberToGroup(String groupid, String username) {
        MongoDatabase db = MongoHelper.getMongoDataBase();
        MongoDao mongoDao = new MongoDaoImpl();
        //两个个临时存储
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        //用户id
        int id = 0;
        String resStr = null;
        int groupleader = 0;
        String table = "user";
        String table1 = "group";
        BasicDBObject userNameObj = new BasicDBObject("username", username);
        BasicDBObject groupidObj = new BasicDBObject("groupid", groupid);
        try {
            list = mongoDao.queryByDoc(db, table, userNameObj);
            id = Integer.parseInt(list.get(0).get("id").toString());
            list1 = mongoDao.queryByDoc(db, table1, groupidObj);
            groupleader = Integer.parseInt(list1.get(0).get("groupleader").toString());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("groupid", groupid);
            jsonObject.addProperty("groupleader", groupleader);
            jsonObject.addProperty("id", id);
            String json = new Gson().toJson(jsonObject);
            Document document = Document.parse(json);
            System.out.println("新成员 + " + json);
            mongoDao.insert(db, table1, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resStr;
    }
}
