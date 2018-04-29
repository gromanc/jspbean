package jspbean.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Component
public class HelloWorldService {

  @Autowired
  private UserNameService userNameService;

  public UserNameService getUserNameService() {
    return userNameService;
  }

  public void setUserNameService(UserNameService userNameService) {
    this.userNameService = userNameService;
  }

  @Inject
  public MoreService moreService;

  public MoreService getMoreService() {
    return moreService;
  }

  public void setMoreService(MoreService moreService) {
    this.moreService = moreService;
  }

  @GET
  @Path("/{param}")
  public Response getMsg(@PathParam("param") String msg) {
    String output = "Jersey say : " + msg;
    return Response.status(200).entity(output).build();

  }

  @GET
  @Path("/name/{i}")
  @Produces(MediaType.TEXT_PLAIN)
  public String userName(@PathParam("i") String i) {
    System.out.println("name::::::::" + moreService.validate());
    return "{\"name\":\"" + i + "\"}";
  }
 }