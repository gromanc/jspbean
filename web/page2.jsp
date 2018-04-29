<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" type="text/css" href="CSS/register.css">
  <title>Register</title>
  <s:head />
  <sj:head loadFromGoogle="true" jqueryui="true" jquerytheme="smoothness"/>
</head>
<body>
<s:form method="POST" action="register" >
  </tr>
  <tr>
    <td colspan="2">
      <s:actionerror/>
      <s:fielderror/>
    </td>
  </tr>
  <s:textfield label="UserID"     key="userId" maxLength="20"/>
  <s:password label="Password"    key="password" maxLength="20"/>
  <s:password label="retype-Password"  key="retypepassword" maxLength="20"/>
  <s:textfield label="Firstname"  key="firstName" maxLength="20"/>
  <s:textfield label="Lastname"   key="lastName" maxLength="20"/>
  <s:textfield label="SecurityLevel" key="securityLevel" maxLength="20"/>
  <s:submit   value="Register"/>
</s:form>
<br>
<input type="button" onclick="add_table_row();">

<div id="div1" style="visibility: hidden">
  <td><s:textfield name="billProductList[0].productDetails.barcode" value="%{billProductList[0].productDetails.barcode}" cssClass="barcode" cssStyle="width:50px"/></td>
  <td><s:textfield name="billProductList[0].productDetails.productTypes.productType" value="%{billProductList[0].productDetails.productTypes.productType}" cssClass="productType" cssStyle="width:250px"/></td>
</div>
<script type="text/javascript">
function add_table_row() {

  var descr = $("#div1").html();
     var num = '123';
     var num1 = parseInt(num) + 1;
 alert('<tr class=\"row\" id=\"'+'item' + num1+'\">' + descr
 );
}
</script>
</body>
</html>
