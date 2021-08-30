package utils;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * MongoDB数据操作接口
 *
 */
public interface MongoDao {

    /**
     * 根据id检索文档
     *
     * @param db
     * @param table
     * @param id
     * @return
     * @throws Exception
     */
    public Map<String, Object> queryByID(MongoDatabase db, String table, Object id) throws Exception;

    /**
     * 根据doc检索文档集合，当doc是空的时候检索全部
     *
     * @param db
     * @param table
     * @param doc
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> queryByDoc(MongoDatabase db, String table, BasicDBObject doc) throws Exception;

    /**
     * 检索全部返回集合
     *
     * @param db
     * @param table
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> queryAll(MongoDatabase db, String table) throws Exception;

    /**
     * 遍历迭代器返回文档集合
     *
     * @param iterable
     * @return
     * @throws Exception
     */
    public List<Document> findIterable(FindIterable<Document> iterable) throws Exception;

    /**
     * 插入文档
     *
     * @param db
     * @param table
     * @param doc
     * @return
     * @throws Exception
     */
    public boolean insert(MongoDatabase db, String table, Document doc) throws Exception;

    /**
     * 插入多条文档
     *
     * @param db
     * @param table
     * @param doc
     * @return
     * @throws Exception
     */
    public boolean insertMany(MongoDatabase db, String table, List<Document> doc) throws Exception;

    /**
     * 删除文档
     *
     * @param db
     * @param table
     * @param doc
     * @return
     * @throws Exception
     */
    public boolean delete(MongoDatabase db, String table, BasicDBObject doc) throws Exception;

    /**
     * 删除单条文档
     *
     * @param db
     * @param table
     * @param doc
     * @return
     * @throws Exception
     */
    public boolean deleteOne(MongoDatabase db, String table, BasicDBObject doc) throws Exception;

    /**
     * 修改文档
     *
     * @param db
     * @param table
     * @param oldDoc
     * @param newDoc
     * @return
     * @throws Exception
     */
    public boolean update(MongoDatabase db, String table, BasicDBObject oldDoc, BasicDBObject newDoc) throws Exception;

    /**
     * 修改单条文档
     *
     * @param db
     * @param table
     * @param whereDoc
     * @param updateDoc
     * @return
     * @throws Exception
     */
    public boolean updateOne(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc)
            throws Exception;

    /**
     * 创建集合
     *
     * @param db
     * @param table
     * @throws Exception
     */
    public void createCol(MongoDatabase db, String table) throws Exception;

    /**
     * 删除集合
     *
     * @param db
     * @param table
     * @throws Exception
     */
    public void dropCol(MongoDatabase db, String table) throws Exception;

}
