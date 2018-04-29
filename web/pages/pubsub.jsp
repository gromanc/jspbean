<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <sj:head />
</head>
<body>
<s:textfield id="account" name="account"  onblur="$(this).publish('accountChanged', this, event);"/>
<s:select id="accountList" name="accountList" list="{'list1', 'list2','list3'}" onblur="$(this).publish('accountListChanged', this, event)"/>
<script type="text/javascript">
  $.subscribe('accountChanged', function(event, data) {
    alert("accountChanged: " + data.value);
  });
  $.subscribe('accountListChanged', function(event, data) {
    var val = data.parentElement.value;
    alert("accountListChanged: " + val);
  });
</script>
<s:select id="accountList2" name="accountList2" list="{'list11', 'list22','list33'}" onblur="confirmEvent(event);"/>
<script type="text/javascript">
  function confirmEvent(event){
    var domElement =$(event.target);
    alert("domElement: " + domElement.attr('id'));
    console.log(domElement.attr('id'));
  }
</script>

</body>
</html>