package com.control.user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:RC
 * @Date:2019/5/29
 * @Description:
 */
@WebFilter(filterName = "Filter",urlPatterns = "*.jsp")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }



    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession();

        String path=request.getRequestURI();
        System.out.println(path);


        if(path.equals("/")||path.contains("index")){
            chain.doFilter(req, resp);
        }else if(path.contains("books.jsp")){
            Boolean login_success=(Boolean)session.getAttribute("login_success");
            System.out.println(login_success);
            if(login_success==null||!login_success){
                response.sendRedirect("/user?state=index");
            }
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
