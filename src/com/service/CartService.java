package com.service;

import com.dao.book.BookDao;
import com.dao.book.impl.BookDaoImpl;
import com.entity.book.Book;
import com.entity.cart.Cart;
import com.entity.cart.CartItem;

import java.util.Map;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public class CartService {
    BookDao bookDao=new BookDaoImpl();

    public void addBook(int bookId, Cart cart){
        Book book=bookDao.queryByBookId(bookId);
        cart.add(book);

    }

    public void deleteBook(int bookId,Cart cart){

        Map map=cart.getMap();
        map.remove(bookId);
    }

    public void clearCart(Cart cart){

        cart.getMap().clear();
    }

    public void updateCart(Cart cart,int bookId,int number){


        CartItem cartItem=cart.getMap().get(bookId);
        cartItem.setNumber(number);
    }



}
