<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <sj:head jqueryui="true"/>
  <title></title>
</head>
<body>

<h2>Datepicker</h2>

<s:url var="remoteurl" action="jsonsample"/>
<script type="text/javascript">
 $.subscribe("changeTopic", function(event,data) {
//     $("#form1").resetForm();
   alert($("#spinner2").val());
 });
 $.subscribe("selectTopic", function(event,data) {
   alert("selected");
 });

</script>

<p class="text">
  A simple Select
</p>
<sj:select
       href="%{remoteurl}"
       name="iform.language"
       list="languageObjList"
       listKey="myKey"
       listValue="myValue"
       emptyOption="true"
       headerKey="-1"
       headerValue="Please Select a Language"
 />

</br>
<s:form id="form1" theme="xhtml" action="savepicker">

  <sj:spinner
    onclick="$.publish('selectTopic')"
    label="Select a Number"
          name="spinner2"
          id="spinner2"
          onChangeTopics="changeTopic"
          min="5"
          max="50"
          step="2"
          value="25"/>

  </br>
  <p class="text">
    A simple Datepicker
  </p>
  <script>
    $.subscribe("completeTopic", function(event,data) {
      alert("completed"+ ",event: "+event.originalEvent.inst+ ",data: "+data );
      event.originalEvent.inst.preventDefault();
    });
  </script>
	<sj:datepicker id="date0" name="date0" value="%{dateValue}" label="Select a Date" displayFormat="mm/dd/yy" onCompleteTopics="completeTopic" />
	<%--<sj:datepicker id="date1" name="date1" value="%{dateValue}" label="Date Value from Action"/>--%>
	<%--<sj:datepicker id="date2" name="nameValue" label="Date Value by Name"/>--%>
	<%--<sj:datepicker id="date3" name="date3" value="today" displayFormat="dd.mm.yy" label="Today"/>--%>
	<%--<sj:datepicker id="date4" name="date4" value="yesterday" displayFormat="mm/dd/yy" label="Yesterday"/>--%>
	<%--<sj:datepicker id="date5" name="date5" value="tomorrow" displayFormat="DD, d MM yy" label="Tomorrow"/>--%>
	<%--<sj:datepicker id="date6" name="date6" value="2004-08-14" displayFormat="d M, yy" label="String Value"/>--%>
	<%--<sj:datepicker id="date7" name="date7" value="today" displayFormat="d M, yy" minDate="minValue" maxDate="maxValue"--%>
	               <%--label="With min and max Date"/>--%>

  <s:submit/>
</s:form>


<div class="code ui-widget-content ui-corner-all">
	<strong>Code:</strong>
    <pre>
    &lt;s:form id=&quot;form&quot; theme=&quot;xhtml&quot;&gt;
      &lt;sj:datepicker id=&quot;date0&quot; name=&quot;date0&quot; maxDate=&quot;-1d&quot; label=&quot;Select a Date&quot; /&gt;
      &lt;sj:datepicker value=&quot;%{dateValue}&quot; id=&quot;date1&quot; name=&quot;date1&quot; label=&quot;Date Value from Action&quot; /&gt;
      &lt;sj:datepicker id=&quot;date2&quot; name=&quot;nameValue&quot; label=&quot;Date Value by Name&quot; /&gt;
      &lt;sj:datepicker value=&quot;today&quot; id=&quot;date3&quot; name=&quot;date3&quot; displayFormat=&quot;dd.mm.yy&quot; label=&quot;Today&quot; /&gt;
      &lt;sj:datepicker value=&quot;yesterday&quot; id=&quot;date4&quot; name=&quot;date4&quot; displayFormat=&quot;mm/dd/yy&quot; label=&quot;Yesterday&quot; /&gt;
      &lt;sj:datepicker value=&quot;tomorrow&quot; id=&quot;date5&quot; name=&quot;date5&quot; displayFormat=&quot;DD, d MM yy&quot; label=&quot;Tomorrow&quot; /&gt;
      &lt;sj:datepicker value=&quot;2004-08-14&quot; id=&quot;date6&quot; name=&quot;date6&quot; displayFormat=&quot;d M, yy&quot; label=&quot;String Value&quot; /&gt;
    &lt;/s:form&gt;
    </pre>
</div>
</body>
</html>