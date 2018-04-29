<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 28.09.2014
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title></title>
</head>
<body>
<s:form action="checkbox-list">
friuts that i like : <s:checkboxlist name="myFruits" list="{'apple','mango','orange'}"/>
  <s:submit/>
</s:form>
Result:
my favorite fruit is:<br>
<s:property value="myFruits"/>
</body>
</html>
