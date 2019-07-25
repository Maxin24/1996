package book;
import com.dao.book.BookDao;
import com.dao.book.impl.BookDaoImpl;
import org.junit.Test;
/**
 * @Author:RC
 * @Date:2019/5/30
 * @Description:
 */
public class TestBook {
    BookDao bookDao=new BookDaoImpl();

    @Test
    public void testListAllBooks(){
        System.out.println(bookDao.listAllBooks());
    }
}
