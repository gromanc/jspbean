<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 16.02.13
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title>Result</title>
</head>
<body>
<s:property value="%{#action.result}" />

<s:optiontransferselect
    label="Favourite Cartoons Characters"
    name="leftSide"
    addToLeftLabel="<"
    list="{'Master', 'Chief Officer', '2nd Officer'}"
    doubleName="rightSide"
    doubleList=""

    />
</body>
</html>