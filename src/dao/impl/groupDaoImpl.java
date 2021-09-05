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
    public String AddMemberToGroup(String textno, String username) {
        MongoDatabase db = MongoHelper.getMongoDataBase();
        MongoDao mongoDao = new MongoDaoImpl();
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        int id = 0;
        String resStr = null;
        String groupid = null;
        int groupleader = 0;
        String table = "user";
        String table1 = "group";
        String table2 = "article";
        BasicDBObject userNameObj = new BasicDBObject("username", username);
        BasicDBObject textnoObj = new BasicDBObject("textno", textno);
        try {
            list = mongoDao.queryByDoc(db, table, userNameObj);
            for (int i = 0; i < list.size(); i++) {
                id = Integer.parseInt(list.get(0).get("id").toString());
            }
            list1 = mongoDao.queryByDoc(db, table2, textnoObj);
            for (int j = 0; j < list1.size(); j++) {
                groupid = list1.get(0).get("groupid").toString();
                groupleader = Integer.parseInt(list1.get(0).get("groupleader").toString());
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
                mongoDao.insert(db, table1,document);
                resStr = "申请成功！";
            } else {
                //有的话就返回
                resStr = "您已经在这个小组了！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resStr;
    }
}
