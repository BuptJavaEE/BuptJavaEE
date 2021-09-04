package web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.MessageService;
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

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-04-10
 */
@WebServlet("/deletemessageservlet")
public class deleteMessageServlet extends HttpServlet {
    public String json = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            this.json = acceptjson;
            System.out.println("返回数据 + " + json);
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String time = jsonObject.get("time").getAsString();
            String username = jsonObject.get("username").getAsString();
            MessageService messageService = new MessageServiceImpl();
            messageService.removeMessage(username,time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
