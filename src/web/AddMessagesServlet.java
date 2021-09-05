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
import pojo.User;
import service.impl.MessageServiceImpl;
import utils.MongoDao;
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
            //设置时间
            JsonObject jsonObject = JsonParser.parseString(acceptjson).getAsJsonObject();
            jsonObject.addProperty("date", date.toString());
            jsonObject.addProperty("standardDate", times);
            String textno = jsonObject.get("textno").getAsString();
            String username = jsonObject.get("username").getAsString();
            //判断
            if (jsonObject.get("type").getAsString().equals("pass")) {
                groupDao groupDao = new groupDaoImpl();
                resStr = groupDao.AddMemberToGroup(textno, username);
            }
            else if (jsonObject.get("type").getAsString().equals("refuse")) {

            }
            else if(jsonObject.get("type").getAsString().equals("apply")){
                BasicDBObject textnoObj = new BasicDBObject("textno",textno);
                MongoDao mongoDao = new MongoDaoImpl();
                MongoDatabase db = MongoHelper.getMongoDataBase();
                String table = "article";
                List<Map<String,Object>> list = mongoDao.queryByDoc(db,table,textnoObj);
                String groupid = list.get(0).get("groupid").toString();
                String table2 = "group";
                BasicDBObject groupidObj = new BasicDBObject("groupid",groupid);
                List<Map<String,Object>> list1 = mongoDao.queryByDoc(db,table2,groupidObj);
                int groupleaderid = Integer.parseInt(list1.get(0).get("groupleader").toString());
                String table3 = "user";
                BasicDBObject idObj = new BasicDBObject("id",groupleaderid);
                List<Map<String,Object>> list2 = mongoDao.queryByDoc(db,table3,idObj);
                String nickname = list2.get(0).get("username").toString();
                jsonObject.addProperty("towho",nickname);
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
            out.print(resStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
