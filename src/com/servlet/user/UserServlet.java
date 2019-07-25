package com.servlet.user;

import com.dao.cart.CartDao;
import com.dao.cart.impl.CartDaoImpl;
import com.dao.user.UserDao;
import com.dao.user.impl.UserDaoImpl;
import com.entity.cart.Cart;
import com.entity.user.User;
import com.util.web.WebContents;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:RC
 * @Date:2019/5/27
 * @Description:User类的servlet，用来实现登录和注册功能
 */
@WebServlet(name ="UserServlet",urlPatterns ={"/user"})
public class UserServlet extends HttpServlet {
    private UserDao userDao=new UserDaoImpl();
    private CartDao cartDao=new CartDaoImpl();
    public UserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     *@Author:RC
     *@Date: 2019/5/27
     *@Discription:
     *@param
     *@return: void
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType(("text/html;charset=UTF-8"));
        String state=req.getParameter("state");
        switch (state){
            case "index":
                req.getRequestDispatcher(WebContents.INDEX).forward(req,resp);
                break;
            case "login":
                adminLogin(req,resp);
                break;
            case "userLogin":
                check(req,resp);
                req.getRequestDispatcher(WebContents.USERLOGIN).forward(req,resp);
                break;
            case "userLogin_do":
                userLogin(req,resp);
                break;
            case "reset":
                reset(req,resp);
                break;
            case "userRegister":
                req.getSession().setAttribute("registerResult",null);
                req.getRequestDispatcher(WebContents.USERREGISTER).forward(req,resp);
                break;
            case "userRegister_do":
                register(req,resp);
                break;
            case "userUpdate":
                req.getRequestDispatcher(WebContents.USERUPDATE).forward(req,resp);
                break;
            case "userUpdate_do":
                userUpdate(req,resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            case "update_do":
                String id2=(String)req.getSession().getAttribute("updateId");
                update(req,resp,Integer.parseInt(id2));
                break;
            case "admin":
                admin(req,resp);
                break;
            case "adminLogin":
                req.getSession().setAttribute("sessionUsername",null);
                req.getSession().setAttribute("loginResult",null);
                req.getRequestDispatcher(WebContents.ADMINLOGIN).forward(req,resp);
                break;
            case "add_do":
                adminAddUser(req,resp);
                break;
            case "addUser":
                req.getRequestDispatcher(WebContents.ADMIN_ADD_USER).forward(req,resp);
                break;
            case "fuzzyQuery":
                fuzzyQuery(req,resp);
                break;
            case "userInfo":
                req.getRequestDispatcher(WebContents.USERINFO).forward(req,resp);
                break;
            case "logout":
                req.getSession().setAttribute("sessionIsLogin",null);
                req.getSession().setAttribute("loginResult",null);
                req.getSession().setAttribute("loginSituation1",null);
                req.getSession().setAttribute("loginSituation2",null);
                req.getRequestDispatcher(WebContents.INDEX).forward(req,resp);
                break;
            case "check":
                if(req.getSession().getAttribute("sessionIsLogin")==null){
                    req.getRequestDispatcher(WebContents.USERLOGIN).forward(req,resp);
                }
                break;
            case "check1":
                if(req.getSession().getAttribute("sessionAdminIsLogin")==null){
                    req.getRequestDispatcher(WebContents.ADMINLOGIN).forward(req,resp);
                }
                break;
            case "adminLogoff":
                req.getSession().setAttribute("sessionAdminIsLogin",null);
                req.getRequestDispatcher(WebContents.INDEX).forward(req,resp);
        }

        if(req.getSession().getAttribute("sessionIsLogin")==null){
            req.getSession().setAttribute("loginSituation","<a href=\"user?state=userLogin\">登录</a>");
        }else if(req.getSession().getAttribute("sessionIsLogin").equals("true")){
            req.getSession().setAttribute("loginSituation","<a href=\"user?state=userInfo\">欢迎您！"+req.getSession().getAttribute("sessionUsername")+"</a>");
            req.getSession().setAttribute("loginSituation1","<a href=\"user?state=logout\">退出登录</a>");
            req.getSession().setAttribute("loginSituation2","<a href=\"cart?state=jumpToCart\">购物车</a>");
        }

    }

    private void userUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String password=request.getParameter("password");
        String intro=request.getParameter("introduction");
        String username=(String)request.getSession().getAttribute("sessionUsername");
        User user=userDao.queryByUsername(username);
        userDao.updateUserById(user.getId(),username,password,(int)request.getSession().getAttribute("sessionUserAccount"),(String)request.getSession().getAttribute("sessionIsAdmin"));
        request.getSession().setAttribute("sessionPassword",password);
        request.getSession().setAttribute("sessionUserIntroduction",intro);
        request.getRequestDispatcher(WebContents.USERINFO).forward(request,response);
    }

    private void reset(HttpServletRequest req,HttpServletResponse resp){
        req.getSession().setAttribute("login_success",false);
//        req.getSession().setAttribute("sessionUsername",null);
//        req.getSession().setAttribute("sessionPassword",null);
//        req.getSession().setAttribute("sessionAccount",null);
//        req.getSession().setAttribute("sessionIsAdmin",null);
        req.getSession().setAttribute("login_success",true);
    }

    private void update(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String id1=request.getParameter("id");
        User user=userDao.queryById(Integer.parseInt(id1));
        request.getSession().setAttribute("updateId",id1);
        request.getSession().setAttribute("updateUsername",user.getUsername());
        request.getSession().setAttribute("updatePassword",user.getPassword());
        request.getSession().setAttribute("updateAccount",user.getAccount());
        if(user.getIsManager().equals("是")){
            request.getSession().setAttribute("updateIsAdmin","checked=\"checked\"");
        }
        request.getRequestDispatcher(WebContents.ADMINUPDATE).forward(request,response);
    }

    private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String id=request.getParameter("id");
        if(userDao.deleteUserById(Integer.parseInt(id))){
            admin(request,response);
        }
    }

    private void admin(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        request.getSession().setAttribute("updateIsAdmin",null);
        List<User> list=userDao.listAllUsers();
        request.setAttribute("list",list);
        request.getRequestDispatcher(WebContents.ADMIN).forward(request,response);
    }

    private void adminLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        UserDao userDao=new UserDaoImpl();
        String username=request.getParameter("account");
        String password=request.getParameter("password");
        User user=userDao.queryByUsername(username);
        if(user!=null&&user.getIsManager().equals("是")){
            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("sessionUsername",user.getUsername());
                List<User> list=userDao.listAllUsers();
                request.setAttribute("list",list);
                request.getSession().setAttribute("updateIsAdmin",null);
                request.getSession().setAttribute("sessionAdminIsLogin","true");
                request.getRequestDispatcher(WebContents.ADMIN).forward(request,response);

            }
            else {
                request.getSession().setAttribute("sessionUsername",user.getUsername());
                request.getSession().setAttribute("loginResult",2);
                request.getRequestDispatcher(WebContents.ADMINLOGIN).forward(request,response);
            }
        }else {
            request.getSession().setAttribute("loginResult",1);
            request.getRequestDispatcher(WebContents.ADMINLOGIN).forward(request,response);
        }
    }

    private void userLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        UserDao userDao=new UserDaoImpl();
        String username=request.getParameter("account");
        String password=request.getParameter("password");
        String ck=request.getParameter("ck");
        System.out.println(ck);
        if(ck!=null){
            Cookie cookie=new Cookie("users",username+"-"+password);
            cookie.setMaxAge(600);
            response.addCookie(cookie);
        }else if(ck==null){
            request.getSession().setAttribute("sessionUsername1",null);
            request.getSession().setAttribute("sessionPassword1",null);
            Cookie[] cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("users")){
                    cookie.setMaxAge(0);
                }
            }
        }
        User user=userDao.queryByUsername(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                if(request.getSession().getAttribute("cart")==null){
                    Cart cart=cartDao.loadCart(username);
                    request.getSession().setAttribute("cart",cart);
                }

                request.getSession().setAttribute("sessionUserId",user.getId());
                request.getSession().setAttribute("sessionUsername",user.getUsername());
                request.getSession().setAttribute("sessionPassword",user.getPassword());
                request.getSession().setAttribute("sessionUserIntroduction",user.getIntoduction());
                request.getSession().setAttribute("sessionUserAccount",user.getAccount());
                request.getSession().setAttribute("sessionUserIconUrl",user.getIconUrl());
                request.getSession().setAttribute("sessionIsAdmin",user.getIsManager());
                request.getSession().setAttribute("sessionIsLogin","true");
                String path = request.getContextPath();
                String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";request.setAttribute("basePath", basePath);
                request.getSession().setAttribute("basePath",basePath);

//                request.getSession().setAttribute("userIcon","Username_"+username+".jpg");
                request.getRequestDispatcher(WebContents.INDEX).forward(request,response);
            }else {
                request.getSession().setAttribute("sessionUsername",user.getUsername());
                request.getSession().setAttribute("loginResult",2);
                request.getRequestDispatcher(WebContents.USERLOGIN).forward(request,response);
            }
        }else {
            request.getSession().setAttribute("loginResult",1);
            request.getRequestDispatcher(WebContents.USERLOGIN).forward(request,response);
        }
    }

    private void register(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        UserDao userDao=new UserDaoImpl();
        String username=request.getParameter("account");
        String password=request.getParameter("password");
        User user=new User();
        user.setIconUrl("defaultUser.jpg");
        user.setIsManager("否");
        user.setPassword(password);
        user.setUsername(username);
        user.setIntoduction("这个人很懒，什么都没有留下。");
        user.setAccount(0);
        User user1=userDao.queryByUsername(username);
        if(user1!=null){
            request.getSession().setAttribute("registerResult",1);
            request.getRequestDispatcher(WebContents.USERREGISTER).forward(request,response);
        }else if(userDao.register(user)){
//            userDao.updateUserHeadByUsername("D:\\javaworkspace\\OneLastDance\\web\\resources\\img\\defaultUserIcon.jpg",username);
            request.getRequestDispatcher(WebContents.INDEX).forward(request,response);
        }
    }

    private void update(HttpServletRequest request,HttpServletResponse response,int id) throws IOException,ServletException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int account=Integer.parseInt(request.getParameter("balance"));
        String isAdmin=request.getParameter("isAdmin");
        System.out.println(isAdmin);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        if(isAdmin==null){
            isAdmin="否";
        }

        if(userDao.updateUserById(id,username,password,account,isAdmin)){
            RequestDispatcher rd=request.getRequestDispatcher("/user?state=admin");
            rd.forward(request,response);
        }else {
            System.out.println("更新失败");
        }
    }

    private void adminAddUser(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String username=request.getParameter("username");
        User user1=userDao.queryByUsername(username);
        if(user1==null){
            String password=request.getParameter("password");
            int account=Integer.parseInt(request.getParameter("balance"));
            System.out.println(account);
            String isAdmin=request.getParameter("isAdmin");
            User user=new User();
            user.setAccount(account);
            user.setUsername(username);
            user.setPassword(password);
            if(isAdmin==null){
                isAdmin="否";
            }
            user.setIsManager(isAdmin);
            if(userDao.register(user)){
                request.getRequestDispatcher("/user?state=admin").forward(request,response);
            }
        }else {
            if(user1.getIsManager().equals("是"))
                request.getSession().setAttribute("addUserResult",1);
            else {
                request.getSession().setAttribute("addUserResult",2);
            }
            request.getRequestDispatcher(WebContents.ADMIN_ADD_USER).forward(request,response);
        }

    }

    private void fuzzyQuery(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        String s=request.getParameter("fuzzyQuery");
        List<User> list=userDao.fuzzyQueryByUsername(s);
        request.setAttribute("list",list);
        request.getRequestDispatcher(WebContents.ADMIN).forward(request,response);
    }

    private void check(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("users")){
                String username=cookie.getValue().split("-")[0];
                String password=cookie.getValue().split("-")[1];

                request.getSession().setAttribute("sessionUsername1",username);
                request.getSession().setAttribute("sessionPassword1",password);
            }
        }
    }
}
