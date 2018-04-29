package jspbean.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class SimpleServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    try {
////      getServletContext().getRequestDispatcher("/showAjaxLoginForm").forward(request, response);
//      request.getRequestDispatcher("showAjaxLoginForm").forward(request, response);
//    } catch (ServletException e) {
//      e.printStackTrace();  //Todo change body of catch statement.
//    }
    PrintWriter out;
    String search = new String(request.getParameter("search").getBytes("ISO-8859-1"),"UTF-8");
    System.out.println("file.encoding=" + System.getProperty("file.encoding"));
    System.out.println("sun.jnu.encoding=" + System.getProperty("sun.jnu.encoding"));
    System.out.println("sun.io.unicode.encoding=" + System.getProperty("sun.io.unicode.encoding"));
    System.out.println("file.encoding.pkg=" + System.getProperty("file.encoding.pkg"));
    System.out.println(search);
    System.out.println(new String(new String(search.getBytes("UTF8")).getBytes("UTF8")));
    System.out.println(new String(search.getBytes("UTF-8"), "UTF8"));
    PrintStream ps = new PrintStream(System.out, true, "UTF8");
    System.setOut(ps);
    System.out.println(new String(search.getBytes("UTF8"), "UTF-8"));
    System.out.println(new String(search.getBytes("UTF-8"), "UTF8"));
    System.out.println(search);
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    out = response.getWriter();
    out.println("<html><head><title>simple webapp</title></head>");
    out.println("<body><p>This is a simple webapp.</p>");
    out.println("<body><p>You are looking for &quot;" + search + "&quot;.</p>");
    out.println("</body></html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }

}
