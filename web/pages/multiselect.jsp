<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <title>jQuery MultiSelect Widget Demo</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/css/jquery.multiselect.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/assets/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/assets/prettify.css'/>" />
<sj:head jqueryui="true" jquerytheme="ui-lightness" loadAtOnce="true" />
<script type="text/javascript" src="<s:url value='/assets/prettify.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.multiselect.js'/>"></script>
<script type="text/javascript">
$(function(){
	$("select").multiselect();
});
</script>
</head>
<body id="test" onload="prettyPrint();">

<h2>Basic Demos</h2>

<p>Both multiselects are created with the following one-liner.  Optgroup support is built in out of the box:</p>
<pre class="prettyprint">
$(function(){
   $("select").multiselect(); 
});
</pre>
<h3>Basic</h3>
<s:form action="multiselect" method="POST" theme="simple">
<p>
	<s:select title="Basic example"
          multiple="true"
          name="languageList"
	        list="languageObjList"
	        listKey="myKey"
	        listValue="myValue"/>
</p>

<h3>With Optgroups</h3>
<p>Click on an optgroup's heading to toggle the checked state of the entire group.</p>
<p>
	<select name="example-optgroup" multiple="multiple" size="5">
	<optgroup label="Group One">
		<option value="option1">Option 1</option>
		<option value="option2">Option 2</option>
		<option value="option3">Option 3</option>
	</optgroup>
	<optgroup label="Group Two">
		<option value="option4">Option 4</option>
		<option value="option5">Option 5</option>
		<option value="option6">Option 6</option>
		<option value="option7">Option 7</option>
	</optgroup>
	</select>
</p>
<sj:submit/>
</s:form>
</body>
</html>
