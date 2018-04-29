package jspbean.struts;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Test10 {

  public static Connection getConnection() throws Exception {
    String driver = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://localhost/databaseName";
    String username = "root";
    String password = "root";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }

//  public static void main(String[] args) throws Exception {
//    Dog dog = new Dog();
////    Connection con = getConnection();
////  PreparedStatement ps = con.prepareStatement("SELECT a,b,c FROM mytable WHERE category = ?");
////    ps.setString(1, "my/super/category/abc\\(def");
//  System.out.println("category/abc\\(def".replaceAll("\\(", "\\("));
//    System.out.println("category/abc\\(def");
//
//    int n = 2000000000;
//
//
//  }

  static class Dog extends Test10{
     public Dog(){
       Dog("max");

     }
    public Dog(String name){
      int exp =100;
      if (exp>100)
      System.out.println(name);
    }
    public void Dog(String n){
      System.out.println(n);
    }
    public static void main(String[] args){
   List<String> l1 = new ArrayList<>();
      List<Integer> l2 = new ArrayList<>();
      System.out.println (l1.getClass() == l2.getClass());
      System.out.println (Math.sqrt(-17));
      Object o = new Object() {
        public boolean equals(Object o){
          return true;
        }
      };
    }
  }
}

interface IA {

}

interface IB {

}