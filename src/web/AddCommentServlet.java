package web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.*;
import dao.impl.*;
import pojo.Comment;
import pojo.Message;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-12
 */
@WebServlet("/addcommentsevlet")
public class AddCommentServlet extends HttpServlet {
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
            JsonObject jsonObject = JsonParser.parseString(acceptjson).getAsJsonObject();

            //取东西
            int textno = jsonObject.get("textno").getAsInt();
            String title = jsonObject.get("title").getAsString();
            String nickname = jsonObject.get("user").getAsString();

            //插入评论
            Comment comment = new Gson().fromJson(acceptjson, Comment.class);
            CommentDao commentDao = new CommentDaoImpl();
            commentDao.saveComment(comment);

            //奇怪的初始化
            ArticleDao articleDao = new ArticleDaoImpl();
            groupDao groupDao = new groupDaoImpl();
            UserDao userDao = new UserDaoImpl();
            List<Integer> authors = new ArrayList<>();
            String name = null;
            int groupid = articleDao.queryGroupidByTextno(textno);
            authors = groupDao.queryAuthorsByGroupId(groupid);

            //插入消息
            for (int i = 0; i < authors.size(); i++) {
                int id = authors.get(i);
                User tempuser = userDao.queryUserByUserId(id);
                name = tempuser.getUsername();
                Message message = new Message("suggest", title, nickname, name);
                new MessageDaoImpl().saveMessage(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
