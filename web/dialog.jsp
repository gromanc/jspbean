<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>

<head><title>Dialog jsp page</title>
  <meta charset="utf-8" />
 <title>jQuery UI Dialog - Default functionality</title>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <sj:head loadFromGoogle="true" jqueryui="true" jquerytheme="smoothness"/>
 <script>
 $(function() {
 $( "#dialog" ).dialog();
 });
 </script>
 </head>
<body>
<div id="dialog" title="Basic dialog">
<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>

</body>
</html>