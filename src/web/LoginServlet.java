package web;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类<code>LoginServlet</code>用于:用户登录的servlet
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-14
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        // 调用 userService.login()登录处理业务
        User loginUser = new UserDaoImpl().queryUserByUsername(username);
        // 如果等于null,说明登录 失败!
        if(loginUser == null){
            //跳回登陆界面
            req.getRequestDispatcher("/pages/User/login.html").forward(req, resp);
        }
        else{
            //登录 成功
            System.out.println("用户登陆成功");
            //传送json数据给谁谁
            //将数据存储到session
            req.getSession().setAttribute("User",loginUser);
            resp.sendRedirect("pages/Writer/Welcome.jsp");
        }
    }
}
