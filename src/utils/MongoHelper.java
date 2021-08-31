package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.LinkedBlockingQueue;

public class MongoHelper {

    static final String DBName = "littleterm";
    static final String ServerAddress = "47.94.108.20";
    static final int PORT = 27017;

    private MongoHelper() {
    }

    private static MongoClient mongoClient = new MongoClient(ServerAddress, PORT);

    // 模拟连接池(阻塞队列)
    private static LinkedBlockingQueue<MongoDatabase> mongoDatabases = new LinkedBlockingQueue<MongoDatabase>();


    public static void closeMongoClient(MongoDatabase mongoDatabase) {
        mongoDatabases.add(mongoDatabase);
    }

    public static MongoDatabase getMongoDataBase() {
        try {
            MongoDatabase mDatabase = mongoClient.getDatabase(DBName);
            mongoDatabases.add(mDatabase);
            MongoDatabase Database = mongoDatabases.take();
            return Database;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
