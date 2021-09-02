package dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.CommentDao;
import org.bson.Document;
import pojo.Comment;
import pojo.Message;
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
 * @date 2021-09-02-11
 */
public class CommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> queryCommentsByUsername(String username) {
        List<Map<String, Object>> list = new ArrayList<>();//存储查询结果用的表
        List<Comment> reslist = new ArrayList<>();//结果表
        Comment comment = new Comment();//临时存储
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        BasicDBObject usernameObj = new BasicDBObject("username", username);
        String table = "comment";
        try {
            list = mongoDao.queryByDoc(db, table, usernameObj);
            for (Map<String, Object> map : list) {
                String Json = new Gson().toJson(map);
                comment = new Gson().fromJson(Json, Comment.class);
                reslist.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslist;
    }

    @Override
    public void saveComment(Comment comment) {
        MongoDao mongoDao = new MongoDaoImpl();
        MongoDatabase db = MongoHelper.getMongoDataBase();
        String table = "comment";
        String Json = new Gson().toJson(comment);
        Document document = Document.parse(Json);
        try {
            if (mongoDao.insert(db, table, document))
                System.out.println("插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
