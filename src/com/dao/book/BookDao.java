package com.dao.book;

import com.entity.book.Book;

import java.util.List;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public interface BookDao {
    public List<Book> listAllBooks();

    public List<Book> fuzzyQuerybyWritername(String Writername);

    public List<Book> fuzzyQuerybyBookname(String Bookname);


    public List<Book> queryByType(String type);

    public List<Book> queryByStyle(String style);

    public Book queryByBookId(int bookId);
}
