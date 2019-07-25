package com.entity.cart;

import com.entity.book.Book;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public class CartItem {
    private Book book;
    private int number;
    private int price;

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", number=" + number +
                ", price=" + price +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return book.getPrice()*number;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
