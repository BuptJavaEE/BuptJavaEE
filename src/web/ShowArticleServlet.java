package web;

import dao.impl.ArticleDaoImpl;
import pojo.User;
import service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-01-07
 */
@WebServlet("/showarticleservlet")
public class ShowArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            User loginUser=(User)req.getSession().getAttribute("User");
            String articles = new ArticleServiceImpl().loadArticles(loginUser.getId());
            out.print(articles);
            System.out.println(articles);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
