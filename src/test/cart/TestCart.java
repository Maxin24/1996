package cart;

import com.dao.cart.CartDao;
import com.dao.cart.impl.CartDaoImpl;
import org.junit.Test;

/**
 * @Author:RC
 * @Date:2019/6/3
 * @Description:
 */
public class TestCart {
    CartDao cartDao=new CartDaoImpl();

    @Test
    public void testAddItem(){
        cartDao.addItem("admin",1,1);
    }

    @Test
    public void testUpdateItem(){
        cartDao.updateItem("admin",1,2);
    }

    @Test
    public void testQueryByUsernameAndBookId(){
        System.out.println(cartDao.queryItemByUsernameAndBookId("admin",1));
    }

    @Test
    public void testDeleteItemByUsernameAndBookId(){
        System.out.println(cartDao.deleteItemByUsernameAndBookId("admin",1));
    }

    @Test
    public void testLoadCart(){
        System.out.println(cartDao.loadCart("admin"));
    }

    @Test
    public void testGetNumber(){
        System.out.println(cartDao.getItemNumber("admin",1));
    }
}
