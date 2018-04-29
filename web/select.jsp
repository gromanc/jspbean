<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>--%>
  <sj:head />
  <title>Select and JSON-RPCs</title>
</head>
<body>

<h2>Select and JSON-RPCs</h2>

<p class="text">
	A simple Select
</p>
<s:url var="remoteurl" action="jsonsample"/>
<sj:select
        href="%{remoteurl}"
        name="iform.language"
        list="languageObjList"
        listKey="myKey"
        listValue="myValue"
        emptyOption="true"
        headerKey="-1"
        headerValue="Please Select a Language"
        cssStyle="background: blue;"
  />
<br>
<div id="div1">Div 1</div>

<s:url var="remoteurl2" action="jsonsample" escapeAmp="false"><s:param name="dblField" value="3.14"/></s:url>
<sj:a href="%{#remoteurl2}" targets="div1">Json table dblField</sj:a>
<br>

<br>
<s:a id="remoteurl3">Next Application/JSON</s:a>
<script type="text/javascript">
$(document).ready(function(){
  $("#remoteurl3").click(function(){
    $.ajax({
       type: "POST",
       url: "<s:property value="#remoteurl"/>",
       data: JSON.stringify({dblField: 3.14}),
       dataType:"JSON",
       contentType: "application/json; charset=utf-8"
    });
  });
});
</script>

<br>

<s:url var="remoteurl3" action="jsonrpcsample"/>
<s:a href="#" id="remoteurl5">Next JSON-RPC</s:a>
<script type="text/javascript">
$(document).ready(function(){
  $("#remoteurl5").click(function(){
    var userId = 1, stateId = 2;
    $.ajax({
      type: "POST",
      url: "<s:property value="#remoteurl3"/>"+".action",
      data: JSON.stringify({jsonrpc:'2.0',method:'gotUser', id:'jsonrpc', params:[userId, stateId]}),
      dataType:"JSON",
      contentType: "application/json-rpc; charset=utf-8",
      success: function(response) {
        var user= response.result;
        alert(JSON.stringify(user));
       }
    });
  });
});
</script>

<br>
<s:a href="#" id="remoteurl6">JSON-RPC Array</s:a>
<script type="text/javascript">
$(document).ready(function(){
  $("#remoteurl6").click(function(){
    var pararray= [
      [2, 0.10, 1.00],
      [1, 0.10, 1.00]
    ];
    $.ajax({
      type: "POST",
      url: "<s:property value="#remoteurl3"/>"+".action",
      data: JSON.stringify({jsonrpc:'2.0',method:'putArray', id:'jsonrpc', params:[pararray]}),
      dataType:"JSON",
      contentType: "application/json-rpc; charset=utf-8",
      success: function(response) {
        var result= response.result;
        alert(JSON.stringify(result));
       }
    });
  });
});
</script>

<br>
<s:a href="#" id="remoteurl7">GET Int Array</s:a>
<script type="text/javascript">
$(document).ready(function(){
  $("#remoteurl7").click(function(){
    var pararray= [2, 10, 1];
    $.get("<s:url action="doGetIntArray"/>", {int_array: pararray}, function(response) {
            var result= response;
            alert(JSON.stringify(result));
           }, "JSON");
  });
});
</script>
<br>
<s:a href="#" id="remoteurl8">POST Object Array</s:a>
<script type="text/javascript">
    $(document).ready(function(){
        $("#remoteurl8").click(function(){
            var count = 1;
            var data1= [];
            for(var i=1;i<=count;i++){
                var obj={};
                obj.name='name'+i;
                obj.url='url'+i;
                data1.push(obj);
            }
            alert($.param(data1));
            <%--$.post("<s:url action="doPostObjectArray"/>", $.param({ 'objects':data1, mode:"insert" }), function(response) {--%>
                <%--var result= response;--%>
                <%--alert(JSON.stringify(result));--%>
            <%--}, "JSON");--%>
        });
    });
</script>

<br>
<input type="button" onclick="">
<script type="text/javascript">
    function IntrvNot(value, options, rowObject){
        var option = {1:"Yes", 2: "No"};
        var radioHtml = '<select>';
        $.each(option, function(key, val){

            radioHtml+='<option value="'+key+'"'+(key==value?' selected="selected"':'')+'>'+val+'</option>';
        });
        radioHtml+='</select>';
        return radioHtml;
    }
</script>
<div id="div1"></div>
<script type="text/javascript">
    $("#div1").html(IntrvNot(2,[],null));
</script>
<br>
<s:iterator var="menu" value="userMenu.get('permissions')">
  <s:property value="#menu.name"/>
</s:iterator>
</body>
</html>