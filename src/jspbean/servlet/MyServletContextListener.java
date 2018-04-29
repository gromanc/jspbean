package jspbean.servlet;

import com.opensymphony.xwork2.config.entities.PackageConfig;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.DispatcherListener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Created by Roma on 27.09.2014.
 */
public class MyServletContextListener implements ServletContextListener, DispatcherListener {

//  @Resource(name="jdbc/jspbean")
  private DataSource ds;

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    System.out.println("contextInitialized");
    ServletContext context = servletContextEvent.getServletContext();
    context.setAttribute("ds",ds);
    Dispatcher.addDispatcherListener(this);
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    System.out.println("contextDestroyed");
  }

  @Override
  public void dispatcherInitialized(Dispatcher du) {
    System.out.println("!!! dispatcherInitialized !!!");
  }

  @Override
  public void dispatcherDestroyed(Dispatcher du) {
    System.out.println("!!! dispatcherDestroyed !!!");
  }

}
