package com.entity.writer;

import com.entity.book.Book;

import java.util.Arrays;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class Writer {
    private int id;
    private String nationality;
    private String name;
    private String introduction;
    private String works;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", nationality='" + nationality + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", works='" + works + '\'' +
                '}';
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

}
