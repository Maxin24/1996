package com.sql.book;

/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class BookSql {
    public static final String listAllBooks="select * from t_book";

    public static final String queryByType="select * from t_book where type=?";

    public static final String queryByStyle="select * from t_book where style=?";

    public static final String fuzzyQueryByWritername="select * from t_book where writer like ?";

    public static final String fuzzyQueryByBookname="select * from t_book where name like ? or writer like ?";

    public static final String queryByBookId="select * from t_book where id=?";
}
