<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 03.11.2014
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url var="keyUrl" action="pubsub"><s:param name="%{#key}" value="%{#keyValue}"/></s:url>
1. <s:property value="#keyUrl"/><br>
