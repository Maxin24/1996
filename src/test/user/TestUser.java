package user;

import com.dao.user.UserDao;
import com.dao.user.impl.UserDaoImpl;
import com.entity.user.User;
import org.junit.Test;

/**
 * @Author:RC
 * @Date:2019/5/27
 * @Description:User的测试类，用来测试UserDaoImpl中的各种方法
 */
public class TestUser {
    UserDao userDao=new UserDaoImpl();

    @Test
    public void testQueryByUsername(){
        System.out.println(userDao.queryByUsername("admin"));
    }

    @Test
    public void testListAllUsers(){
        System.out.println(userDao.listAllUsers());
    }

    @Test
    public void testRegister(){

        User user=new User();
        user.setUsername("zxt");
        user.setPassword("111111");
        user.setAccount(100);
        user.setIsManager("否");
        userDao.register(user);
    }

    @Test
    public void testFuzzyQueryByUsername(){
        System.out.println(userDao.fuzzyQueryByUsername("e"));
    }

    @Test
    public void testDeleteUser(){
        System.out.println(userDao.deleteUserById(17));
    }

    @Test
    public void testUpdateUser(){
        System.out.println(userDao.updateUserById(2,"ljx","111111",10,"否"));
    }

    @Test
    public void testUpdateUserHeadById(){
        System.out.println(userDao.updateUserHeadById("D:\\demo2.jpg",1));
    }

    @Test
    public void testGetHeadById(){
        userDao.getUserHeadById(1);
    }

    @Test
    public void testUpdateIntroByUsername(){
        userDao.updateUserIntroByUsername("admin","666");
    }
}
