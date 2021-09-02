package web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.ArticleDao;
import dao.CommentDao;
import dao.impl.ArticleDaoImpl;
import pojo.Comment;
import service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类<code>ShowCommentServlet</code>用于:展示评论的servlet
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-05-22
 */
@WebServlet("/showcommentservlet")
public class ShowCommentServlet extends HttpServlet {
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
            json = acceptjson;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            //连接数据库,获取评论
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String username = jsonObject.get("username").getAsString();
            int textno = jsonObject.get("textno").getAsInt();
            List<Comment> comments = new CommentServiceImpl().loadComments(username);
            Comment tempcomment = new Comment();
            List<String> res = new ArrayList<>();
            for (Comment comment : comments) {
                if (comment.getTextno() == textno) {
                    tempcomment = comment;
                    String json = new Gson().toJson(tempcomment);
                    res.add(json);
                }
            }

            //浏览次数+1
            ArticleDao articleDao = new ArticleDaoImpl();
            articleDao.updateBrowsetimes(textno);

            //将评论转换为json类型
            out.print(res);
            System.out.println("返回数据" + json);
            System.out.println("输出数据" + res);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
