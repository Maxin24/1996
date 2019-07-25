package com.sql.cart;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public class CartSql {
    public static final String addItem="insert into t_cart(username,bookId,number) values(?,?,?)";
    public static final String updateItem="update t_cart set number=? where bookId=? and username=?";
    public static final String queryItemByUsernameAndBookId="select * from t_cart where bookId=? and username=?";
    public static final String deleteItemByUsernameAndBookId="delete from t_cart where bookId=? and username=?";
    public static final String loadCart="select * from t_cart where username=?";
    public static final String getItemNumber="select * from t_cart where username=? and bookId=?";
    public static final String deleteAll="delete from t_cart where username=?";
}
