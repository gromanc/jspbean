<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
</head>
<body>
<b>REGISTER</b>
<p>Please fill up the form below.</p>
<s:form action="register" method="post">
  <s:textfield label="Enter username" key="userId" maxlength="25"
               size="30" />
  <s:textfield label="Enter email" key="userEmail1" type="email"
               placeholder="someone@domain.com" size="30" />
  <s:textfield label="Re-enter email" key="userEmail2" type="email"
               placeholder="someone@domain.com" size="30" />
  <s:password label="Enter password" key="userPassword1" size="30" />
  <s:password label="Re-enter password" key="userPassword2"
              size="30" />
  <tr><td>
  <img id="captchaImg" src="<s:url action='captcha'/>" alt="Captcha Image" height="45">
  <img src="<c:url value='/images/reload.jpg' />" alt="Reload" onclick="document.forms[0].captchaImg.src='<s:url action='captcha'/>'+'?id='+Math.random();" style="cursor:pointer"/>
  <s:textfield label="Enter CAPTCHA" key="captchaResponse" size="30" requiredLabel="*"/>
  <tr><td>
  Cannot read? Refresh page for new CAPTCHA.
  </td></tr>
  <s:submit method="create" value="Register" />
</s:form>

</body>
</html>