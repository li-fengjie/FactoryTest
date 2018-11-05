package com.life.servlet;

import com.life.utils.CommonsUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传 common-uploadFile.jar
 */
@WebServlet("/BServlet")
public class BServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        /**
         * @param 缓存文件大小
         * @param 缓存文件存储临时路径
         */
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(20*1024,new File("D:\\java_code\\temp"));
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        /*
         * 限制单个文件的上传大小
         * 必须在解析之前
         */
        upload.setFileSizeMax(500 * 1024);
        /*
         * 限制整个表单上传大小
         * 必须在解析前设置
         */
        upload.setSizeMax(1024*1024);
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            FileItem fileItem = fileItems.get(0);
            FileItem fileItem1 = fileItems.get(1);
            System.out.println("普通表单项："+fileItem.getFieldName() + " " +fileItem.getString("UTF-8"));
            System.out.println("文件表单项：" +fileItem1.getContentType());
            System.out.println(fileItem1.getSize());
            System.out.println(fileItem1.getName());

            String root = this.getServletContext().getRealPath("/WEB-INF/files");

            /*
                处理文件名是绝对路径问题
             */
            String saveName = fileItem1.getName();
            int index = saveName.lastIndexOf("\\");
            if(index != -1){
                saveName = saveName.substring(index+1);
            }
            saveName = CommonsUtils.getUUID() + "_" + saveName;
            /*
             * 打散文件存储（根据哈希值）
             *
             */
            int hCode = saveName.hashCode();
            /*
             *转换成16进制
             */
            String hex = Integer.toHexString(hCode);
            /*
            生成完整路径
             */
            File dirFile = new File(root,hex.charAt(0)+"/" + hex.charAt(1));
            //创建目录链
            dirFile.mkdirs();
            //保存文件
            File saveFile = new File(dirFile,saveName);
            System.out.println(saveFile.toString());
//            File destFile = new File("D:/1.jpg");
            fileItem1.write(saveFile);
        } catch (FileUploadException e) {
//            单个文件超出大小限制
            if(e instanceof FileUploadBase.FileSizeLimitExceededException){

            }
        } catch (Exception e) {
            //整个表单大小超出限制
            if(e instanceof FileUploadBase.SizeLimitExceededException){

            }
            throw new RuntimeException(e);
        }

    }
}
