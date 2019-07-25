package com.servlet.upload;

import com.dao.user.UserDao;
import com.dao.user.impl.UserDaoImpl;
import com.entity.user.User;
import com.util.web.WebContents;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author:RC
 * @Date:2019/6/2
 * @Description:
 */
@WebServlet(name = "UploadServlet",urlPatterns = {"/upload"})
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY="upload";
    private static final int MEMORY_THRESHOLD=1024*1024*3;
    private static final int MAX_FILE_SIZE=1024*1024*40;
    private static final int MAX_REQUEST_SIZE=1024*1024*50;
    private static int NumberOfImagesUploaded=0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(req)){
            return;
        }

        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload=new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
        String uploadPath=req.getRealPath("resources/img/");
        String uploadPath1="D:\\javaworkspace\\OneLastDance\\web\\resources\\img";
        File uploadDir1=new File(uploadPath1);
        File uploadDir=new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        if(!uploadDir1.exists()){
            uploadDir1.mkdir();
        }

        try{
            @SuppressWarnings("unchecked")
            List<FileItem> formItems=upload.parseRequest(req);
            if(formItems!=null&&formItems.size()>0){
                for(FileItem item:formItems){
                    if(!item.isFormField()){
                        String username=(String)req.getSession().getAttribute("sessionUsername");
                        String fileName="Username_"+username+NumberOfImagesUploaded+".jpg";
                        NumberOfImagesUploaded++;
//                        String fileName=new File(item.getName()).getName();

                        String filePath=uploadPath+File.separator+fileName;
                        String filePath1=uploadPath1+File.separator+fileName;
                        File storeFile=new File(filePath);
                        File storeFile1=new File(filePath1);
                        item.write(storeFile1);
                        item.write(storeFile);
                        UserDao userDao=new UserDaoImpl();
                        User user=userDao.queryByUsername((String)req.getSession().getAttribute("sessionUsername"));
                        String userIconUrl=fileName;
                        user.setIconUrl(userIconUrl);
                        userDao.updateUserIconByUsername(userIconUrl,username);
                        req.getSession().setAttribute("sessionUserIconUrl",fileName);

                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }




        req.getRequestDispatcher(WebContents.USERINFO).forward(req,resp);

    }
}
