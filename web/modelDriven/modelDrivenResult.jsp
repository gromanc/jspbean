<%@taglib prefix="s" uri="/struts-tags" %>
<%--<html>--%>
<%--<head>--%>
	<%--<title>Struts2 Showcase - Model Driven Example - Result</title>--%>
	<%--<style type="text/css">--%>
		<%--.label {--%>
			<%--background-color: #ffffff;--%>
			<%--color: #000000;--%>
			<%--text-shadow: none;--%>
			<%--font-weight: bold;--%>
		<%--}--%>
	<%--</style>--%>
<%--</head>--%>

<%--<body>--%>
<script>alert("123");</script>
<div class="page-header">
	<h1>Model Driven Example - Result</h1>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">

			<s:label
					label="Gangster Name"
					name="name"/><br/>
			<s:label
					label="Gangster Age"
					name="age"/><br/>
			<s:label
					label="Busted Before"
					name="bustedBefore"/><br/>
			<s:label
					label="Gangster Description"
					name="description"/><br/>
      <s:iterator value="questionIds">
        <s:label
            label="Question"
            name="questionMap[top].name"/><br/>
      </s:iterator>

		</div>
    <s:label
        label="Par1"
        name="par1"/>
    <br/>

	</div>
</div>
<%--</body>--%>
<%--</html>--%>