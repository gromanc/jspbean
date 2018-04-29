package jspbean.websocket;

import jspbean.hibernate.persistence.UserDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Creating a Server End point using Annotation.
 * This server end point is available at "/<app_path>/webSocket".
 */
@ServerEndpoint(value="/webSocket", configurator=ServletAwareConfig.class)
@Component
public class WebSocketDemoEndPoint  {

  /**
   * counter to track count of messages received.
   * This instance variable is specific to one client. Which means that
   * for each client connection one instance of WebSocketDemoEndPoint is
   * created.
   */
  int counter = 1;

  public WebSocketDemoEndPoint() {
    System.out.println("WebSocketDemoEndPoint()" );
  }

  private UserDAO userDAO;

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  private EndpointConfig config;

  @OnOpen
  public void onOpen(Session websocketSession, EndpointConfig config) {
    this.config = config;
  }

  /**
   * Method which receives the messages from the client and sends messages
   * back to the server.
   */
  @OnMessage
  public void receiveMessage(String message, Session session) throws IOException{
    System.out.println("From websocket: " + message);

    HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
    ServletContext servletContext = httpSession.getServletContext();

    //Sending the messages/data to client synchronously.
    session.getBasicRemote().sendText("Sending message: "+ counter++ +" from server");
  }


}