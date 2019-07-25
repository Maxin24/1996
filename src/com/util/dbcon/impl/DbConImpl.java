package com.util.dbcon.impl;

import com.util.dbcon.DbCon;

import java.sql.*;

/**
 * @Author:RC
 * @Date:2019/5/26
 * @Description: 一个工具类 负责操作部分与数据库有关的对象
 */
public class DbConImpl implements DbCon {
    private final String DRIVER="com.mysql.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/db_old";
    private final String USERNAME="root";
    private final String PASSWORD="123ljx456";
    private Connection con=null;

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   加载JDBC驱动程序
     *@param: [con]
     *@return:
     */
    public DbConImpl() {

    }

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   获取连接
     *@param: []
     *@return: java.sql.Connection
     */
    @Override
    public Connection getConnection() {

        try{
            Class.forName(DRIVER);
            con= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public void closeConnection(Connection con) {
        if(con!=null){
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   关闭预处理结果对象
     *@param: [ps]
     *@return: void
     */
    @Override
    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try{
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   关闭结果集对象
     *@param: [rs]
     *@return: void
     */
    @Override
    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try{
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param rs
     * @param ps
     * @param con
     * @Author:RC
     * @Date: 2019/5/27
     * @Discription:三个全关了
     * @return: void
     */
    @Override
    public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
        closeResultSet(rs);
        closePreparedStatement(ps);
        closeConnection(con);
    }
}
