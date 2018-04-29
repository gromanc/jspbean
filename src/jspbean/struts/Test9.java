package jspbean.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test9 {

  User user;
  Club club;
  Domain domain;

  static class ClubDao {
    public static boolean isActive(User user, Club club) {
      return false;
    }
  }

  static class Club {
    String name;

    public Club(String s) {
      this.name = s;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  class Domain {
    String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDefaultUrl() {
      return null;
    }
  }
  class User {
    String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  static Map<Club, String> redirectMap= new LinkedHashMap<Club, String>();

  static  {
    redirectMap.put(new Club("kidclub.google.mobi"), "youthclub.yahoo.mobi");
    redirectMap.put(new Club("youthclub.yahoo.mobi"), "adult.godaddy.mobi");
    redirectMap.put(new Club("adult.godaddy.mobi"), "elderly.google.mobi");
    redirectMap.put(new Club("elderly.google.mobi"), "kidclub.google.mobi");

  }

  void redirectReturningUser (HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<Club, String> tmpRredirectMap = new LinkedHashMap<Club, String>();
    for (Map.Entry<Club, String> entry: redirectMap.entrySet()){

      Club club = entry.getKey();
      String redirectUrl = entry.getValue();
      if( user != null && club != null && ClubDao.isActive(user, club))  {
        tmpRredirectMap.put(club, redirectUrl);
      }
    }
    boolean done = false;
    String tempUrl = domain.getDefaultUrl();
    int count = redirectMap.size();
    Club tempClub = new Club(tempUrl);
    do {
      if (tmpRredirectMap.keySet().contains(tempClub)){
        tempClub = new Club(redirectMap.get(tempClub));
        count--;
      } else {
        done = true;
      }
    } while (! done && count > 0);
    redirectReturningUser(request, response, tempClub.getName());
  }
  void redirectReturningUser( HttpServletRequest request, HttpServletResponse  response, String redirectUrl ) throws IOException {

    doRedirect(response, "http://"+redirectUrl );
  }

  private void doRedirect(HttpServletResponse response, String s) throws IOException {
    response.sendRedirect(s);
  }
}
