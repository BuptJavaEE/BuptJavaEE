package filter;

import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//实现filter 拦截那些未登录的用户
@WebFilter("/pages/Writer/*")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
//        该方法专门用于拦截请求过滤响应，可以做权限检查
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
       Object user = httpServletRequest.getSession().getAttribute("User");
        if (user ==null){
            //不放行
            System.out.println("拦截器启动 请先登陆");
            servletRequest.getRequestDispatcher("/pages/User/login.html").forward(servletRequest, servletResponse);
            return;
        }else {
            //让程序继续访问 放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
