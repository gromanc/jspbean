<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <sj:head />
  <%--<sx:head />--%>
  <script type="text/javascript" src="<s:url value="/js/kiev.js"/>" language="javascript"></script>
</head>

<body>
<table class="maintab">
  <tbody>
  <tr>
    <td class="main">
      <div class="header">
        <%--
        <s:div id="loginDiv">
          <div id="showauth" class="userinfo"><br clear="all"><a href="javascript:void(0);" onclick="doShowAuth();"><s:text name="label.enterUser"/></a></div>
          <div id="authpanel" style="display: block;" class="authpanel" >
            <div class="topauth"></div>
            <div class="close"><img src="<s:url value="/images/x.gif"/>" alt="закрыть" onclick="doCloseAuth();"></div>
            <div id="authform" class="authform">
              <s:form id="ajaxLoginForm" action="ajaxLogin"  validate="false" method="post" target="_top">
                <table id="authtable">
                  <tbody>
                  <tr>
                    <td colspan="2">
                      <s:actionerror />
                      <s:fielderror />
                    </td>
                  </tr>
                  <tr>
                    <td><s:text name="label.login"/></td>
                    <td><s:textfield name="username" maxlength="50" size="17" /></td>
                  </tr>
                  <tr>
                    <td><s:text name="label.password"/></td>
                    <td><s:password name="password" maxlength="50" size="17" /></td>
                  </tr>
                  <tr>
                    <td width="100%"><label for="USER_REMEMBER"><s:text name="label.remember"/></label></td>
                    <th><input id="USER_REMEMBER" name="USER_REMEMBER" value="Y" align="right" type="checkbox">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <sx:submit id="loginSubmit" cssStyle="padding: 0px 6px;" value="%{getText('button.enter')}"  targets="loginDiv" notifyTopics="/ajaxLogin"/>
                    </th>
                  </tr>
                  </tbody>
                </table>
                <s:a action="register"><s:text name="label.register"/></s:a> | <s:a action="forgot-password"><s:text name="label.forgotPassword"/></s:a>
              </s:form>
            </div>
          </div>
        </s:div>
        --%>



        <div class="userinfo"><br clear="all"><a href="javascript:void(0);" class="showauth"><s:text name="label.enterUser"/></a></div>
        <div class="authpanel">
          <div class="topauth"></div>
          <div class="close"><img src="<s:url value="/images/x.gif"/>" alt="<s:text name="label.close"/>"></div>
          <div class="authform">
            <s:url id="ajl" value="ajaxLogin"/>
            <s:a href="%{ajl}">Login URL</s:a>
            <s:form id="ajaxLoginForm" namespace="/" action="ajaxLogin"  validate="false" method="post" target="_top">
              <table>
                <tbody>
                <tr>
                  <td colspan="2">
                    <s:div id="loginDiv"/>
                  </td>
                </tr>
                <tr>
                  <td><s:text name="label.login"/></td>
                  <td><s:textfield name="username" maxlength="50" size="17" /></td>
                </tr>
                <tr>
                  <td><s:text name="label.password"/></td>
                  <td><s:password name="password" maxlength="50" size="17" /></td>
                </tr>
                <tr>
                  <td width="100%"><label for="remember"><s:text name="label.remember"/></label></td>
                  <th><input id="remember" name="remember" value="Y" type="checkbox" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<sj:submit cssStyle="padding: 0px 6px;" value="%{getText('button.enter')}" executeScripts="true" targets="loginDiv" /></th>
                </tr>
                </tbody>
              </table>
              <s:a action="register"><s:text name="label.register"/></s:a> | <s:a action="forgot-password"><s:text name="label.forgotPassword"/></s:a>
            </s:form>
          </div>
          <div class="botauth"></div>
        </div>



      </div>
    </td>
  </tr>
  </tbody>
</table>

</body>
</html>