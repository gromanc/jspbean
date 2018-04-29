<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
  <head>
  <sj:head debug="true"/>
  </head>
  <body>
  <s:div id="loginDiv" theme="ajax">
  <div style="width: 300px;border-style: solid">
  <s:form action="ajaxloginCancel"  validate="true">
  <tr>
  <td colspan="2">
  Login
  </td>
  </tr>
  <tr>
  <td colspan="2">
  <s:actionerror />
  <s:fielderror />
  </td>
  <s:textfield name="username" label="Login name"/>
  <s:password name="password" label="Password"/>
  <sj:submit value="Submit" theme="ajax" targets="loginDiv"  notifyTopics="/ajaxloginCancel"/>
  <s:submit action="showAjaxLoginCancelForm" value="Cancel" onclick="form.onsubmit=null"/>

  </s:form>
  </div>
  </s:div>
  </body>
</html>