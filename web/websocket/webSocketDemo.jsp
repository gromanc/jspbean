<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 14.09.2014
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>WebSocket Demo</title>
  <script src="<s:url value='/js/webSocket.js'/>"></script>
</head>
<body>
<input type="button" id="startWsConn" value="Connect via websocket" />
<input type="button" id="closeWsConn" value="Close the websocket" />
<div id="wsStatus"></div><br/>
<input type="button" id="sendWsData" value="Send via Websocket" />
<div id="wsSendStatus"></div><br/>
<h3>Messages sent from the server</h3>
<ul id="msgServer">

</ul>
</body>
</html>
