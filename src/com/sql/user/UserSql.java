package com.sql.user;

/**
 * @Author:RC
 * @Date:2019/5/26
 * @Description:存放操作用户数据库的SQL语句
 */
public class UserSql {
    /**
     * 增加新用户
     */
    public static String addUser="insert into t_user(username,password,ismanager,account,introduction,image) value(?,?,?,?,?,?)";

    /**
     * 按照用户名查找用户
     */
    public static String queryByUsername="select * from t_user where username=?";

    public static String queryByUserId="select * from t_user where id=?";
    /**
     * 得到所有用户
     */
    public static String listAll="select * from t_user";

    /**
     * 通过用户名模糊查询用户
     */
    public static String fuzzyQueryByUsername="select * from t_user where username like ?";

    public static String deleteUser="delete from t_user where id=?";

    public static String updateUser="update t_user set username=?,password=?,account=?,ismanager=? where id=?";

    public static String updateUserIconById="update t_user set image=? where id=?";

    public static String updateUserIconByUsername="update t_user set image=? where username=?";

    public static String updateUserIntroByUsername="update t_user set introduction=? where username=?";

    public static String updateAccountById="update t_user set account=? where id=?";
}
