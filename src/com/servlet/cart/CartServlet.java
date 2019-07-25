package com.servlet.cart;

import com.dao.book.BookDao;
import com.dao.book.impl.BookDaoImpl;
import com.dao.cart.CartDao;
import com.dao.cart.impl.CartDaoImpl;
import com.dao.user.UserDao;
import com.dao.user.impl.UserDaoImpl;
import com.entity.cart.Cart;
import com.entity.cart.CartItem;
import com.entity.user.User;
import com.service.CartService;
import com.util.web.WebContents;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */

@WebServlet(name = "CartServlet",urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    private CartDao cartDao=new CartDaoImpl();
    private CartService cartService=new CartService();
    private UserDao userDao=new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType(("text/html;charset=UTF-8"));
        String state=req.getParameter("state");
        switch (state){
            case "add":
                add(req,resp);
                break;
            case "del":
                delete(req,resp);
                break;
            case "clear":
                clear(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            case "buyAll":
                buyAll(req,resp);
                break;
            case "jumpToCart":
                jumpToCart(req,resp);
                break;
        }
    }
    private void jumpToCart(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        if(request.getSession().getAttribute("cart")==null){
            Cart cart=cartDao.loadCart((String)request.getSession().getAttribute("sessionUsername"));
            request.getSession().setAttribute("cart",cart);
            request.getRequestDispatcher(WebContents.USERCART).forward(request,response);
        }else {
            request.getRequestDispatcher(WebContents.USERCART).forward(request,response);
        }
    }

    private void loadCart(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
        if(req.getSession().getAttribute("sessionIsLogin")==null){
            req.getRequestDispatcher(WebContents.USERLOGIN).forward(req,resp);
        }
        int bookId=Integer.parseInt(req.getParameter("bookId"));
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
        }
        cartService.addBook(bookId,cart);
        String username=(String)req.getSession().getAttribute("sessionUsername");
        if(cartDao.queryItemByUsernameAndBookId(username,bookId)){
            cartDao.updateItem(username,bookId,cartDao.getItemNumber(username,bookId)+1);
        }else {
            cartDao.addItem(username,bookId,1);
        }
        req.getSession().setAttribute("cart",cart);
        req.getRequestDispatcher(WebContents.USERCART).forward(req,resp);
    }

    private void delete(HttpServletRequest req,HttpServletResponse resp) throws  IOException,ServletException{
        int bookId=Integer.parseInt(req.getParameter("bookId"));
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        String username=(String)req.getSession().getAttribute("sessionUsername");
        cartDao.deleteItemByUsernameAndBookId(username,bookId);
        cartService.deleteBook(bookId,cart);
        req.getRequestDispatcher(WebContents.USERCART).forward(req,resp);
    }

    private void clear(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        String username=(String)req.getSession().getAttribute("sessionUsername");
        cartDao.deleteAll(username);
        cartService.clearCart(cart);
        req.getRequestDispatcher(WebContents.INDEX).forward(req,resp);
    }

    private void update(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        int bookId=Integer.parseInt(request.getParameter("bookId"));
        int number=Integer.parseInt(request.getParameter("number"));
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        String username=(String)request.getSession().getAttribute("sessionUsername");
        cartDao.updateItem(username,bookId,number);

        cartService.updateCart(cart,bookId,number);
        request.getRequestDispatcher(WebContents.USERCART).forward(request,response);
    }

    private void buyAll(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        int account=(int)request.getSession().getAttribute("sessionUserAccount")-cart.getPrice();
        if(account>=0){
            int id=(int)request.getSession().getAttribute("sessionUserId");
            String username=(String)request.getSession().getAttribute("sessionUsername");
            userDao.updateAccountById(account,id);
            cartDao.deleteAll(username);
            cart.getMap().clear();
            request.setAttribute("sessionUserAccount",account);
            BookDao bookDao=new BookDaoImpl();
            request.setAttribute("list",bookDao.listAllBooks());

            request.getRequestDispatcher(WebContents.ORIGINPAGE).forward(request,response);
        }else {
            request.getRequestDispatcher(WebContents.USERCART).forward(request,response);
        }

    }
}
