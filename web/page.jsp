<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 22.10.12
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<html> <head> <title>Connection with mysql database</title>
 </head>
 <body>
 <h1>Connection status</h1>
 <%
 try {
 String connectionURL = "jdbc:mysql://localhost:3306/";
 Connection connection = null;
 Class.forName("com.mysql.jdbc.Driver");
 connection = DriverManager.getConnection(connectionURL, "root", "root");
 if(!connection.isClosed())
 %>
 <font size="+3" color="green"></b>
 <%
 out.println("Successfully connected to " + "MySQL server using TCP/IP...");
 connection.close();
 }
 catch(Exception ex){
 %></font>
 <font size="+3" color="red"></b>
 <%
 out.println("Unable to connect to database.");
 }
 %>
 </font></body> </html>