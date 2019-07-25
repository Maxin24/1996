package com.dao.cart.impl;

import com.dao.book.BookDao;
import com.dao.book.impl.BookDaoImpl;
import com.dao.cart.CartDao;
import com.entity.cart.Cart;
import com.entity.cart.CartItem;
import com.service.CartService;
import com.sql.cart.CartSql;
import com.sql.user.UserSql;
import com.util.dbcon.DbCon;
import com.util.dbcon.impl.DbConImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public class CartDaoImpl implements CartDao {
    private DbCon dbCon=new DbConImpl();
    private CartService cartService=new CartService();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public boolean addItem(String username, int bookId, int number) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.addItem);
            ps.setString(1,username);
            ps.setInt(2,bookId);
            ps.setInt(3,number);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }

    @Override
    public boolean updateItem(String username, int bookId, int number) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.updateItem);
            ps.setInt(1,number);
            ps.setInt(2,bookId);
            ps.setString(3,username);
            if(ps.executeUpdate()>0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }

    @Override
    public boolean queryItemByUsernameAndBookId(String username, int bookId) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con = dbCon.getConnection();
            ps = con.prepareStatement(CartSql.queryItemByUsernameAndBookId);
            ps.setInt(1, bookId);
            ps.setString(2, username);
            rs = ps.executeQuery();
            if (rs.next())
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return false;
    }

    public boolean deleteItemByUsernameAndBookId(String username,int bookId){
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.deleteItemByUsernameAndBookId);
            ps.setInt(1,bookId);
            ps.setString(2,username);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }

    public Cart loadCart(String username){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Cart cart=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.loadCart);
            ps.setString(1,username);
            rs=ps.executeQuery();
            cart=new Cart();
            while(rs.next()){
                CartItem cartItem=new CartItem();
                cartItem.setBook(bookDao.queryByBookId(rs.getInt("bookId")));
                cartItem.setPrice(cartItem.getPrice());
                cartItem.setNumber(rs.getInt("number"));
                cart.getMap().put(rs.getInt("bookId"),cartItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cart;
    }

    public int getItemNumber(String username,int bookId){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.getItemNumber);
            ps.setString(1,username);
            ps.setInt(2,bookId);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt("number");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return 0;
    }

    public boolean deleteAll(String username){
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(CartSql.deleteAll);
            ps.setString(1,username);
            if(ps.executeUpdate()>0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }
}
