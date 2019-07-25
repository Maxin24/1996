package com.dao.user.impl;

import com.dao.user.UserDao;
import com.entity.user.User;
import com.sql.user.UserSql;
import com.util.dbcon.DbCon;
import com.util.dbcon.impl.DbConImpl;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:RC
 * @Date:2019/5/27
 * @Description:
 */
public class UserDaoImpl implements UserDao {
    private DbCon dbCon;

    @Override
    public User queryById(int id) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.queryByUserId);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccount(rs.getInt("account"));
                user.setIsManager(rs.getString("ismanager"));
                user.setIntoduction(rs.getString("introduction"));
                user.setIconUrl(rs.getString("image"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }
        return user;
    }

    public UserDaoImpl() {
        this.dbCon=new DbConImpl();
    }

    /**
     * @param username
     * @Author:RC
     * @Date:2019/5/26
     * @Discription: 通过用户名查询用户
     * @return:boolean
     */
    @Override
    public User queryByUsername(String username) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.queryByUsername);
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIntoduction(rs.getString("introduction"));
                user.setAccount(rs.getInt("account"));
                user.setIsManager(rs.getString("ismanager"));
                user.setIconUrl(rs.getString("image"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }
        System.out.println(user);
        return user;
    }

    /**
     * @Author:RC
     * @Date:2019/5/26
     * @Discription: 得到所有的用户，存到List里
     * @param
     * @return:java.util.List<com.entity.user.User>
     */
    @Override
    public List<User> listAllUsers() {
        Connection con=null;
        List<User> list=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.listAll);
            rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString("ismanager").equals("否")){
                    User user=new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setIsManager(rs.getString("ismanager"));
                    user.setBooks(rs.getString("booksliked"));
                    user.setAccount(rs.getInt("account"));
                    user.setIconUrl(rs.getString("image"));
                    list.add(user);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }
        return list;
    }

    /**
     * @param user
     * @Author:RC
     * @Date:2019/5/26
     * @Discription: 实现注册功能
     * @return:boolean
     */
    @Override
    public boolean register(User user) {

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.queryByUsername);
            ps.setString(1,user.getUsername());
            rs=ps.executeQuery();
            if(!rs.next()){
                ps=con.prepareStatement(UserSql.addUser);
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getIsManager());
                ps.setInt(4,user.getAccount());
                ps.setString(5,user.getIntoduction());
                ps.setString(6,user.getIconUrl());

                if(ps.executeUpdate()>0){
                    return true;
                }
            }else {
                System.out.println("注册失败 用户已经存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }

        return false;
    }

    /**
     *@Author:RC
     *@Date: 2019/5/30
     *@Discription: 通过用户名模糊查询用户
     *@param
     *@return: java.util.List<com.entity.user.User>
     */
    @Override
    public List<User> fuzzyQueryByUsername(String username) {
        Connection con=null;
        List<User> list=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.fuzzyQueryByUsername);
            ps.setString(1,"%"+username+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString("ismanager").equals("否")){
                    User user=new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setIsManager(rs.getString("ismanager"));
                    user.setBooks(rs.getString("booksliked"));
                    user.setAccount(rs.getInt("account"));
                    list.add(user);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closeAll(rs,ps,con);
        }
        return list;
    }

    /**
     *@Author:RC
     *@Date: 2019/5/30
     *@Discription: 通过id删除用户
     *@param
     *@return: boolean
     */
    @Override
    public boolean deleteUserById(int id) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.deleteUser);
            ps.setInt(1,id);
            if(ps.executeUpdate()>0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }
        return false;
    }


    /**
     *@Author:RC
     *@Date: 2019/5/30
     *@Discription:通过id更新用户信息
     *@param
     *@return: boolean
     */
    @Override
    public boolean updateUserById(int id, String username, String password, int account ,String isAdmin) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.updateUser);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setInt(3,account);
            ps.setString(4,isAdmin);
            ps.setInt(5,id);
            if(ps.executeUpdate()>0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean updateUserHeadById(String imageUrl,int userId) {
        Connection con=null;
        PreparedStatement ps=null;
        InputStream in=null;
        try{
            con=dbCon.getConnection();
            in=new FileInputStream(imageUrl);
            ps=con.prepareStatement(UserSql.updateUserIconById);
            ps.setBinaryStream(1,in,in.available());
            ps.setInt(2,userId);
            if(ps.executeUpdate()>0){
                System.out.println("头像上传成功");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean updateUserIconByUsername(String imageUrl, String username) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.updateUserIconByUsername);
            ps.setString(1,imageUrl);
            ps.setString(2,username);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }

    @Override
    public void getUserHeadById(int id) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        byte[] b=null;
        FileOutputStream out=null;
        InputStream in=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.queryByUserId);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                in=rs.getBinaryStream("image");
                String picName="Username_"+rs.getString("username")+".jpg";
                out=new FileOutputStream("D:\\javaworkspace\\OneLastDance\\web\\resources\\img\\"+picName);
                b=new byte[in.available()];
                int len=0;
                while((len=in.read(b))!=-1){
                    out.write(b,0,len);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            dbCon.closeAll(rs,ps,con);
        }

    }

    @Override
    public boolean updateUserIntroByUsername(String username,String intro) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.updateUserIntroByUsername);
            ps.setString(1,intro);
            ps.setString(2,username);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }

    @Override
    public boolean updateAccountById(int account, int id) {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=dbCon.getConnection();
            ps=con.prepareStatement(UserSql.updateAccountById);
            ps.setInt(1,account);
            ps.setInt(2,id);
            if(ps.executeUpdate()>0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbCon.closePreparedStatement(ps);
            dbCon.closeConnection(con);
        }

        return false;
    }
}
