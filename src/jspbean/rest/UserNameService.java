package jspbean.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Roma on 22.09.2014.
 */

@Path("/user")
@Component
public class UserNameService {
  @GET
  @Path("/name/{i}")
  @Produces(MediaType.TEXT_PLAIN)
  public String userName(@PathParam("i") String i) {
    System.out.println("name::::::::" + i);
    return "{\"name\":\"" + i + "\"}";
  }

}
