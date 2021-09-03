package web;

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
 * 类<code>RegistServlet</code>用于:用户注册的servlet
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-30-14
 */
@WebServlet("/registservlet")
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        //2.检查用户名是否可用
            if(userService.existsUsername(username)){
                System.out.println("用户名["+username+"]已存在！");
                //        跳回注册页面
                resp.sendRedirect("/BuptJavaEE_war_exploded/pages/User/regist.html#"+username);
            }
            else{
                //可用
//                调用Service保存到数据库
                userService.registUser(new User(0, username, password, nickname));
//
//        跳到登录页面 login.html
                resp.sendRedirect("/BuptJavaEE_war_exploded/pages/User/login.html");
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
