package web;

import com.google.gson.Gson;
import dao.impl.ArticleDaoImpl;
import pojo.Article;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-16
 */
@WebServlet("/showallarticleservlet")
public class ShowAllArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            List<Article> list = new ArrayList<>();
            list = new ArticleDaoImpl().queryAllArticles();
            String articles = new Gson().toJson(list);
            out.print(articles);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
