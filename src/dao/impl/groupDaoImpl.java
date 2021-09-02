package dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.groupDao;
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
    public String queryGroupByGroupId(int groupid) {
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
    public List<Integer> queryAuthorsByGroupId(int groupid) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Integer> authorlist = new ArrayList<>();
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject leaderObj = new BasicDBObject("groupid", groupid);
        try {
            list = mongoDao.queryByDoc(db, table, leaderObj);
            for (int i = 0; i < list.size(); i++) {
                int id = Integer.parseInt(list.get(i).get("id").toString());
                authorlist.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorlist;
    }
}
