package dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import dao.ArticleDao;
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
 * @date 2021-08-31-18
 */
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public String getArticles(int id) {
        List<Map<String, Object>> grouplist; //小组列表
        List<Map<String, Object>> articlelist = new ArrayList<>(); //文章列表
        List<Integer> groupidList = new ArrayList<>(); //id列表
        String res;
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "group";
        BasicDBObject idObj = new BasicDBObject("id", id);
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
        return res;
    }

    @Override
    public void updateBrowsetimes(int textno) {
        BasicDBObject textnoObj = new BasicDBObject("textno", textno);
        BasicDBObject updateObj = new BasicDBObject("browsertimes", 1);
        BasicDBObject resObj = new BasicDBObject("$inc", updateObj);
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "article";
        MongoCollection<Document> collection = db.getCollection(table);
        try {
            UpdateResult updateManyResult = collection.updateMany(textnoObj, resObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int queryGroupidByTextno(int textno) {
        int groupid = 0;
        List<Map<String,Object>> list = new ArrayList<>();
        BasicDBObject textnoObj = new BasicDBObject("textno",textno);
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "article";
        MongoDao mongoDao = new MongoDaoImpl();
        try {
            list = mongoDao.queryByDoc(db,table,textnoObj);
            if(list.size() == 1){
                groupid = Integer.parseInt(list.get(0).get("groupid").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupid;
    }
}
