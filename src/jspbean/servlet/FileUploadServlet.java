package jspbean.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUploadServlet extends HttpServlet {

  private static final long serialVersionUID = -3208409086358916855L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    HttpSession session = request.getSession(true);
    if (isMultipart) {
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);

      try {
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {

          if (!item.isFormField()) {
            String fileName = item.getName();
            ServletContext sc = getServletContext();
            String root = sc.getRealPath("/");
            File path = new File(root + "/uploads");
            if (!path.exists()) {
              boolean status = path.mkdirs();
            }

            File uploadedFile = new File(path + "/" + fileName);
            System.out.println(uploadedFile.getAbsolutePath());
            item.write(uploadedFile);
          }
        }
      } catch (FileUploadException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
