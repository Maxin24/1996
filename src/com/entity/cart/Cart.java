package com.entity.cart;

import com.entity.book.Book;
import com.entity.user.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description: 购物车的实体类
 */
public class Cart {
    private int id;
    private Map<Integer,CartItem> map=new HashMap();
    private int price;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", map=" + map +
                ", price=" + price +
                '}';
    }

    public void add(Book book){
        CartItem cartItem=map.get(book.getId());

        if(cartItem==null){
            cartItem=new CartItem();
            cartItem.setBook(book);
            cartItem.setNumber(1);
            map.put(book.getId(),cartItem);
        }else {
            cartItem.setNumber(cartItem.getNumber()+1);
        }
    }

    public int getPrice() {
        int price=0;
        for(Map.Entry<Integer,CartItem> cartItemEntry:map.entrySet()){
            CartItem item=cartItemEntry.getValue();
            price+=item.getPrice();
        }
        return price;
    }

    public Map<Integer, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, CartItem> map) {
        this.map = map;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
