package com.dao.book.impl;

import com.dao.book.BookDao;
import com.entity.book.Book;
import com.entity.user.User;
import com.entity.writer.Writer;
import com.sql.book.BookSql;
import com.util.dbcon.DbCon;
import com.util.dbcon.impl.DbConImpl;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class BookDaoImpl implements BookDao {
    Connection con = null;
    DbCon dbCon = new DbConImpl();

    @Override
    public List<Book> listAllBooks() {
        List<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dbCon.getConnection();
            ps = con.prepareStatement(BookSql.listAllBooks);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                book.setStyle(rs.getString("style"));
                book.setType(rs.getString("type"));
                book.setWritername(rs.getString("writer"));
                book.setImageUrl(rs.getString("imageUrl"));
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbCon.closeAll(rs, ps, con);
        }
        return list;
    }

    @Override
    public List<Book> queryByStyle(String style) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Book> list=new ArrayList<>();
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(BookSql.queryByStyle);
            ps.setString(1,style);
            rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setWritername(rs.getString("writer"));
                book.setStyle(rs.getString("style"));
                book.setType(rs.getString("type"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return list;
    }

    @Override
    public List<Book> queryByType(String type) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Book> list=new ArrayList<>();
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(BookSql.queryByType);
            ps.setString(1,type);
            rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setWritername(rs.getString("writer"));
                book.setStyle(rs.getString("style"));
                book.setType(rs.getString("type"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return list;
    }


    @Override
    public List<Book> fuzzyQuerybyWritername(String Writername) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Book> list=new ArrayList<>();
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(BookSql.fuzzyQueryByWritername);
            ps.setString(1,"%"+Writername+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setWritername(rs.getString("writer"));
                book.setStyle(rs.getString("type"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return list;
    }


    @Override
    public List<Book> fuzzyQuerybyBookname(String Bookname) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Book> list=new ArrayList<>();
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(BookSql.fuzzyQueryByBookname);
            ps.setString(1,"%"+Bookname+"%");
            ps.setString(2,"%"+Bookname+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setWritername(rs.getString("writer"));
                book.setStyle(rs.getString("type"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return list;
    }

    @Override
    public Book queryByBookId(int bookId) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Book book=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(BookSql.queryByBookId);
            ps.setInt(1,bookId);
            rs=ps.executeQuery();
            if(rs.next()){
                book=new Book();
                book.setIntroduction(rs.getString("introduction"));
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setWritername(rs.getString("writer"));
                book.setStyle(rs.getString("type"));
                book.setComment(rs.getString("comment"));
                book.setPrice(rs.getInt("price"));
                book.setImageUrl(rs.getString("imageUrl"));
                return book;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }
        return null;
    }
}



