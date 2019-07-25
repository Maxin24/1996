package com.dao.user;

import com.entity.user.User;

import java.util.List;

/**
 * @Author:RC
 * @Date:2019/5/26
 * @Description:  定义用户类和数据库打交道时候的一些基本方法
 */
public interface UserDao {
    /**
     *@Author:RC
     *@Date:2019/5/26
     *@Discription:  通过用户名查询用户
     *@param username
     *@return:boolean
     */
    public User queryByUsername(String username);

    /**
     *@Author:RC
     *@Date:2019/5/26
     *@Discription:  得到所有的用户，存到List里
     *@param
     *@return:java.util.List<com.entity.user.User>
     */
    public List<User> listAllUsers();

    /**
     *@Author:RC
     *@Date:2019/5/26
     *@Discription:  实现注册功能
     *@param user
     *@return:boolean
     */
    public boolean register(User user);

    public List<User> fuzzyQueryByUsername(String username);

    public boolean deleteUserById(int id);

    public boolean updateUserById(int id,String username,String password,int account,String isAdmin);

    public boolean updateUserHeadById(String imageUrl,int userId);

    public boolean updateUserIconByUsername(String imageUrl,String username);

    public void getUserHeadById(int id);

    public User queryById(int id);

    public boolean updateUserIntroByUsername(String username,String intro);

    public boolean updateAccountById(int account,int id);
}
