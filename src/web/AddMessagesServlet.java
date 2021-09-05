package web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDao;
import dao.groupDao;
import dao.impl.MessageDaoImpl;
import dao.impl.groupDaoImpl;
import pojo.Message;
import pojo.User;
import service.impl.MessageServiceImpl;

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
            } else if (jsonObject.get("type").getAsString().equals("refuse")) {

            }
            Message message = new Gson().fromJson(acceptjson, Message.class);
            MessageDao messageDao = new MessageDaoImpl();
            messageDao.saveMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.print(resStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
