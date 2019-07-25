package com.dao.cart;

import com.entity.cart.Cart;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public interface CartDao {
    public boolean addItem(String username,int bookId,int number);
    public boolean updateItem(String username,int bookId,int number);
    public boolean queryItemByUsernameAndBookId(String username,int bookId);
    public boolean deleteItemByUsernameAndBookId(String username,int bookId);
    public Cart loadCart(String username);
    public int getItemNumber(String username,int bookId);
    public boolean deleteAll(String username);
}
