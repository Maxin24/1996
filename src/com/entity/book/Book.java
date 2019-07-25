package com.entity.book;

import com.entity.Comment.Comment;
import com.entity.writer.Writer;

import java.util.Arrays;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class Book {
    private int id;
    private String name;
    private String writername;
    private String style;
    private String type;
    private String comment;
    private int price;
    private String introduction;
    private String imageUrl;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", writername='" + writername + '\'' +
                ", style='" + style + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", introduction='" + introduction + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWritername() {
        return writername;
    }

    public void setWritername(String writername) {
        this.writername = writername;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }


}
