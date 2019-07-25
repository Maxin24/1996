package com.entity.user;

import com.entity.book.Book;

/**
 * @Author:RC
 * @Date:2019/5/26
 * @Description:用户的实体类
 */
public class User {
    /**
     * 用户的id
     */
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     *是否为管理员
     */
    private String isManager;

    private int account;
    private String books;
    private String intoduction;
    private String iconUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIntoduction() {
        return intoduction;
    }

    public void setIntoduction(String intoduction) {
        this.intoduction = intoduction;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isManager='" + isManager + '\'' +
                ", account=" + account +
                ", books='" + books + '\'' +
                ", intoduction='" + intoduction + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }

}
