package web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import dao.MessageDao;
import dao.groupDao;
import dao.impl.MessageDaoImpl;
import dao.impl.groupDaoImpl;
import pojo.Message;
import utils.MongoDaoImpl;
import utils.MongoHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-10
 */
@WebServlet("/addmessagesservlet")
public class AddMessagesServlet extends HttpServlet {
    String resStr = null;
    JsonObject jsonObject1 = new JsonObject();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用于保存所获取到的数据流
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    req.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            //获取到的json字符串
            String acceptjson = sb.toString();
            //日期
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String times = format.format(date.getTime());
            int id = 0;
            //设置时间
            JsonObject jsonObject = JsonParser.parseString(acceptjson).getAsJsonObject();
            jsonObject.addProperty("date", date.toString());
            jsonObject.addProperty("standardDate", times);
            String textno = jsonObject.get("textno").getAsString();
            String username = jsonObject.get("username").getAsString();
            String groupid = jsonObject.get("groupid").getAsString();
            BasicDBObject userNameObj = new BasicDBObject("username", username);
            MongoDatabase db = MongoHelper.getMongoDataBase();
            MongoDaoImpl mongoDao = new MongoDaoImpl();
            String table = "user";
            List<Map<String, Object>> list = mongoDao.queryByDoc(db, table, userNameObj);
            for (int i = 0; i < list.size(); i++) {
                id = Integer.parseInt(list.get(0).get("id").toString());
            }
            BasicDBObject userIdObj = new BasicDBObject("id", id);
            //判断
            if (jsonObject.get("type").getAsString().equals("pass")) {
                groupDao groupDao = new groupDaoImpl();
                resStr = groupDao.AddMemberToGroup(textno, username);
            } else if (jsonObject.get("type").getAsString().equals("refuse")) {

            } else if (jsonObject.get("type").getAsString().equals("apply")) {
                String table2 = "group";
                BasicDBObject groupidObj = new BasicDBObject("groupid", groupid);
                BasicDBObject andObj = new BasicDBObject("$and", Arrays.asList(groupidObj,userIdObj));
                List<Map<String, Object>> list2 = mongoDao.queryByDoc(db, table2, groupidObj);
                int groupleaderid = Integer.parseInt(list2.get(0).get("groupleader").toString());
                String table3 = "user";
                BasicDBObject idObj = new BasicDBObject("id", groupleaderid);
                List<Map<String, Object>> list3 = mongoDao.queryByDoc(db, table3, idObj);
                String nickname = list3.get(0).get("username").toString();
                jsonObject.addProperty("towho", nickname);
                if (mongoDao.queryByDoc(db, table2, andObj).size() == 0) {
                    resStr = "申请成功！";
                    jsonObject1.addProperty("message",resStr);
                    System.out.println(resStr);
                } else {
                    resStr = "您已经在这个小组了！";
                    jsonObject1.addProperty("message",resStr);
                    System.out.println(resStr);
                    return ;
                }
            }
            String json2 = new Gson().toJson(jsonObject);
            Message message = new Gson().fromJson(json2, Message.class);
            MessageDao messageDao = new MessageDaoImpl();
            messageDao.saveMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            String toJson = new Gson().toJson(jsonObject1);
            out.print(toJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
