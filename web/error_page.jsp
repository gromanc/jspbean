<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error Page</title>
</head>
<body>
<hr>
<h4>Exception Name: <s:property value="exception" /> </h4>
<h4>Exception Details: <s:property value="exceptionStack" /></h4>
</body>
</html>