package com.util.dbcon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author:RC
 * @Date:2019/5/26
 * @Description:  对操作数据库的工具类的一个规范
 */
public interface DbCon {
    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:
     *@param: []
     *@return: java.sql.Connection
     */
    public Connection getConnection();

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:  断开连接
     *@param: []
     *@return: void
     */
    public void closeConnection(Connection con);

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   关闭预处理对象
     *@param: [ps]
     *@return: void
     */
    public void closePreparedStatement(PreparedStatement ps);

    /**
     *@Author:RC
     *@Date: 2019/5/26
     *@Discription:   关闭结果集
     *@param: [rs]
     *@return: void
     */
    public void closeResultSet(ResultSet rs);

    /**
     *@Author:RC
     *@Date: 2019/5/27
     *@Discription:三个全关了
     *@param
     *@return: void
     */
    public void closeAll(ResultSet rs,PreparedStatement ps,Connection con);
}
