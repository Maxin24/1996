package com.servlet.book;
import com.dao.book.BookDao;
import com.dao.book.impl.BookDaoImpl;
import com.entity.book.Book;
import com.util.web.WebContents;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
@WebServlet(name = "BookServlet",urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {
    private BookDao bookDao=new BookDaoImpl();

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
            case "originPage":
                List<Book> list=bookDao.listAllBooks();
                req.getSession().setAttribute("list",list);
                req.getRequestDispatcher(WebContents.ORIGINPAGE).forward(req,resp);
                break;
            case "bookInfo":
                String bookId=req.getParameter("bookId");
                Book book=bookDao.queryByBookId(Integer.parseInt(bookId));
                req.setAttribute("book",book);
                req.getRequestDispatcher(WebContents.BOOKINFO).forward(req,resp);
                break;
            case "listByType":
                String type=req.getParameter("type");
                List<Book> list1=bookDao.queryByType(type);
                req.getSession().setAttribute("list",list1);
                req.getRequestDispatcher(WebContents.ORIGINPAGE).forward(req,resp);
                break;
            case "listByStyle":
                String style=req.getParameter("style");
                List<Book> list2=bookDao.queryByStyle(style);
                req.getSession().setAttribute("list",list2);
                req.getRequestDispatcher(WebContents.ORIGINPAGE).forward(req,resp);
                break;
            case "fuzzyQuery":
                String bookName=req.getParameter("book");
                List<Book> list3=bookDao.fuzzyQuerybyBookname(bookName);
                req.getSession().setAttribute("list",list3);
                req.getRequestDispatcher(WebContents.ORIGINPAGE).forward(req,resp);
                break;
        }
    }
}
