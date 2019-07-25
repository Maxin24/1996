package com.entity.Comment;

import com.entity.user.User;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class Comment {
    private int id;
    private User user;
    private String comment;
    private int likes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                '}';
    }
}
