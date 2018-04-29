<!DOCTYPE html>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="jspbean.beans.SomeBean" %>
<%@ page import="jspbean.hibernate.persistence.User" %>
<%@ page import="jspbean.struts.Questions" %>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="someBean" class="jspbean.beans.SomeBean" scope="request"/>
<jsp:useBean id="someBeanList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="ssl" class="jspbean.beans.SSL" scope="request">

</jsp:useBean>
<%--<jsp:setProperty name="someBean" property="request" value="${pageContext.request}"/>--%>
<%--<jsp:setProperty name="someBean" property="response" value="${pageContext.response}"/>--%>
<%--<jsp:useBean id="link" scope="application" class = "jspbean.beans.SSL" />--%>
<%--<jsp:setProperty name="link" property="prop" value=""/>--%>
<html>
<head>
  <meta charset=UTF-8>
  <title>Simple jsp page</title>
  <script>
    function useApplet(){
      appletId.run();
    }
  </script>
  <%--<s:head/>--%>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <sj:head loadFromGoogle="true" jqueryui="true" jquerytheme="smoothness"/>
  <sb:head/>
  <script>
    $(function() {
      var obj = $.parseJSON('{"findList":[{"col1":"col1","col2":"col2"}]}');

      var mydialog = $("#dialog-form").dialog ({
        autoOpen: false,
        height: 500,
        width: 750,
        modal: true,
        buttons : {
          "Search" : function() {
            alert(JSON.stringify(obj));
            $.ajax({
              type: "POST",
              url : '<s:url action="part" />',
              data: JSON.stringify(obj),
              dataType:"JSON",
              contentType: "application/json; charset=utf-8",
              success : function(data) {
//                  var obj = $.parseJSON(data);
                var obj = data;
                alert(JSON.stringify(obj));
              }
            });
          }
        }
      });

      $("#dialog1").click(function(){
        mydialog.dialog("open");
      });
    });


  </script>

  <%--<script type="text/javascript">--%>
  <%--$(function() {--%>
  <%--$("#dialog").dialog();--%>
  <%--});--%>
  <%--</script>--%>

  <%--<script type="text/JavaScript">--%>
  <%--$(document).ready(function() {--%>
  <%--alertMyVariable(<s:property value="id"/>);--%>
  <%--});--%>
  <%--function alertMyVariable(myVariable) {--%>
  <%--alert(myVariable);--%>
  <%--}--%>
  <%--</script>--%>

</head>
<body>
  <s:if test="hasFieldErrors() || hasActionErrors()">
    <s:actionerror/><br>
    <s:fielderror/><br>
  </s:if>
  Hello Jspbean!<br>
  <br>
  <s:url var="skipToContact" value="#contact"/>
  <s:a href="%{#skipToContact}">Skip to Contact</s:a><br>
  <s:set var = "contentz" value="content" scope="request"/>
  contentz=${content}<br>
  <s:set var="content12" value="%{content = 'Some content'}"/>
  <s:property value="content"/>
  <s:property value="content12"/>
  <%--&lt;%=request.getAttribute("contentz")%&gt;--%>
  <br>
  <div  style="display: block;white-space: nowrap" >
  <s:set var = "frob1" value="1"/><s:set var="frob2" value="2"/>
  <s:radio name="frob%{''+#frob1+#frob2}" list="#@java.util.HashMap@{'Yes':'Yes','No':'No'}" cssStyle="display: inline-block; vertical-align: 50%; padding: 3px;"  /><br>
  frob: <s:property value="%{['frob'+#frob1+#frob2]}"/><br>
  <s:set value="#action.frob12='Another'"/>
  frob12v: <s:property value="#frob12v"/><br>
  frob: <s:property value="%{['frob'+#frob1+#frob2]}"/><br>
  </div>
  <br>
  <%--&lt;%=java.net.URLDecoder.decode(((String[])request.getParameterMap().get("inputFr"))[0], "UTF-8")%&gt;<br>--%>
  <%--<s:property value='@java.net.URLDecoder@decode(#parameters.inputFr, "UTF-8")' /> <br>--%>

  <%--<form name="xyzForm" action="save?id=<%=request.getParameter("id")%>&name=<%=request.getParameter("name")%>&age=<%=request.getParameter("age")%>&num=<%=request.getParameter("num")%>" method="POST">--%>

  <%--<s:debug>--%>
  <s:url var="myFormUrl" namespace="/" action="save" includeContext="false"><s:param name="id" value="%{id}"/></s:url>
  myFormUrl=<s:property value="#myFormUrl"/><br>
  <s:property value="%{#myFormUrl.substring(1)}"/><br>
  <s:set var="contextPath">${pageContext.request.contextPath}</s:set>
  <s:property value="%{#contextPath}"/><br>
  ${pageContext.request.contextPath}<br>
  <s:a id="acno" href="%{#contextPath+#myFormUrl}" includeContext="false"><s:property value="id"/></s:a>
  <s:form id="myForm" name="myForm" action="%{#myFormUrl}" method="POST" theme="simple">
    <%--<s:form name="myForm" action="%{@java.lang.String@valueOf(#myFormUrl).substring(1)}" method="POST">--%>
    <%--<s:hidden name="user" value="<%=request.getParameter("id")%>"/>--%>
    punchOutRes=<input type="text" name="someBean.punchOutRes" value="<jsp:getProperty name="someBean" property="punchOutRes"/>" size="20"/><br>
    <!--punchOutRes=<% SomeBean sb = (SomeBean) someBeanList.get(0); out.println(sb.getPunchOutRes());%> <br>-->
    name=  <%=SomeBean.Type.ROOT.name() %>
    <%! String cargo=""; %>
    <% ssl = someBean.getSsl(); String cargo=null; %>
    <s:set var="c"><%=cargo%></s:set>

    <%--<s:property value="#c" /><br>--%>
    <%--<input type="text" name="prop" value="<jsp:getProperty name="ssl" property="prop"/>"   size="20"/><br>--%>
    <%--&lt;%--<s:textfield name="content2" value='%{@java.net.URLDecoder@decode(#parameters.inputFr, "UTF-8")}'/>--%&gt;--%>
    <%--<input type="text" name="content2" value='médiévaux, principalement pour l’anglais et le français'/> <br>--%>
    <%--<s:textfield name ="mobileno" label="Mobile No" value="%{mobileno}"/> --%>
    <%--<label for="MyForm_mobileno">Mobile number</label>:&nbsp;<input id="MyForm_mobileno" type="text" name="mobileno" /><br>--%>
    <%--<s:if test="hasFieldErrors()"><s:fielderror fieldName="mobileno"/></s:if>--%>
    <%--<label for="MyForm_phone">Phone</label>:&nbsp;<input id="MyForm_phone" type="text" name="phone" /><br>--%>
    <%--<s:if test="hasFieldErrors()"><s:fielderror fieldName="phone"/></s:if>--%>
    <%--<s:submit value="Save" method="save"/>--%>
    <br>
    <% request.setAttribute("title", "Weather+ Free");%>
    <%--<s:set var="title" value=""/>--%>
    title: <s:property value="#request.title"/>
    <br>
    <s:property value="%{content2}"/>
    <br>
    <%--<br>My Questions<br>--%>
    <%--<s:iterator value = "myQuestions" status="key" var="q">--%>
    <%--&lt;%--<s:textfield name = "myQuestions[%{#key.index}].name" /><br>--%&gt;--%>
    <%--<s:textfield name = "myQuestions(%{#q.id}).name" /><br>--%>
    <%--&lt;%--<input type="radio" name="myQuestions[<s:property value="%{#key.index}"/>].ans1" value="<s:property value="%{myQuestions[#key.index].ans1}"/>" <s:if test="%{(#session.myMap.get(myQuestions[#key.index].name)).equals(myQuestions[#key.index].ans1)}">checked</s:if>><s:property value="%{myQuestions[#key.index].ans1}"/><br>--%&gt;--%>
    <%--</s:iterator>--%>
    <%--<br>--%>

    <br>
    List of List
    <br>
    <s:iterator value="%{list}" var="lst" status="st">
      <s:iterator value="%{#lst}" status="st1">
        <s:textfield name="list[%{#st.index}][%{#st1.index}]" value="%{top}"/><br>
      </s:iterator>
    </s:iterator>
    <%--<br>--%>
    <%--Map Questions<br>--%>
    <%--<table>--%>
    <%--<s:iterator var="m" value="map" status="status">--%>
    <%--<tr>--%>
    <%--<s:iterator var="val" value="#m.value" status="status2">--%>
    <%--<td>--%>
    <%--&lt;%&ndash;<s:property value="#val.key"/>, <s:property value="#val.value.name"/>&ndash;%&gt;--%>
    <%--<s:textfield name="map['%{#m.key}']['%{#val.key}']" value="%{map[#m.key][#val.key]}" theme="simple" size="10" />--%>
    <%--</td>--%>
    <%--</s:iterator>--%>
    <%--</tr>--%>
    <%--</s:iterator>--%>
    <%--</table>--%>
    <%--<br>--%>

    <%--Map Questions 2<br>--%>
    <%--<table>--%>
    <%--<tr>--%>
    <%--<s:iterator var="m2" value="map2">--%>
    <%--<td>--%>
    <%--&lt;%--<s:property value="#val.key"/>, <s:property value="#val.value.name"/>--%&gt;--%>
    <%--<s:textfield name="map2['%{#m2.key}'].name" value="%{map2[#m2.key].name}" theme="simple" size="10" />--%>
    <%--</td>--%>
    <%--</s:iterator>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--<br>--%>

    <%--Map Questions 3<br>--%>
    <%--<table>--%>
    <%--<s:iterator var="m3" value="map3">--%>
    <%--<tr>--%>
    <%--<s:iterator var="v3" value="#m3.value">--%>
    <%--<td>--%>
    <%--&lt;%--<s:property value="#v3.key"/>, <s:property value="#v3.value.name"/>--%&gt;--%>
    <%--<s:textfield name="map3['%{#m3.key}']['%{#v3.key}'].name" value="%{map3[#m3.key][#v3.key].name}" theme="simple" size="10" />--%>
    <%--</td>--%>
    <%--</s:iterator>--%>
    <%--</tr>--%>
    <%--</s:iterator>--%>
    <%--</table>--%>
    <%--<br>--%>
    <br>
    <i>myMap Questions</i> <br>
    <%--<s:select headerKey="-1" headerValue="Select Question" listKey="key" listValue="value" list="(myMap.entrySet()).{value}"  onchange="selectGroup(this.value)" theme="xhtml"/>--%>
    <s:select headerKey="-1" headerValue="Select Question"  list="myQuestions.{id +' <i> lives in <i> '+ name }"  onchange="selectGroup(this.value)" theme="xhtml"><s:param name="escapeHtml" value="false"/></s:select>
    <script type="text/javascript">
      function selectGroup(value){
        alert(value);
  //        var v = '%{content2}';
  //        alert(v);
  //        $('#myQuestion'+this.value).show();if (this.value=='q1')$('#myQuestionq2').hide();else $('#myQuestionq1').hide();
      }
    </script>
    <br>

    <s:iterator var="row" value="session.map['myMap']">
      <%--<s:property value="top"/><br>--%>
      <s:if test="session.map['myMap'].keys.contains(top.key)">
        <%--<s:textfield name="myMap['%{key}']" value="%{value}" size="10" /><br>--%>
        <s:textfield id="myQuestion%{key}" name="myQuestion%{key}" value="%{value}" size="10" /><br>
      </s:if>
    </s:iterator>
    <br>
    Map &lt;Long,Long&gt;
    <br>
    <s:textfield name="categoryLogVal[0]" value="1893"/>
    <br>
    <input type="submit"/>
  </s:form>
  <br>
  <s:property value="%{myMethod(myVariable)}" />
  <br>
  <s:set var="myNum" value="'NINE'" />
  <s:set var="number" value="%{'@jspbean.struts.DefaultAction@'+#myNum}" />
  first number <s:property value="#number" />
  <br>
  second number <s:property value="%{#attr[#number]}" />
  <br>
  <s:if test="#attr[#number] == 9">
    should be <s:property value="@jspbean.struts.DefaultAction@NINE" />
  </s:if>
  <br>
  <% int j = 44;%>
  <s:set var="ii"><%=j%></s:set>
  <%application.setAttribute("header_"+j, "value"+j); %>
  <s:set var = "header_ii" value =""/>
  <section id="contact"></section>
  header_44 = <s:property value="('#application.header_'+#ii)(0)"/>
  <br>
  <%--</s:debug>--%>
  <%--<br>--%>
  <%--<s:set var="userIndex" value="%{@java.lang.Integer@valueOf(#parameters.index)}" />--%>
  <%--<s:property value="#userIndex" /><br>--%>
  <%--<s:property value="%{myQuestions[#userIndex].name}" />--%>
  <br>
  <a href="simple?search=m%C3%A9di%C3%A9vaux,%20principalement%20pour%20l%E2%80%99anglais%20et%20le%20fran%C3%A7ais">Simple Search</a><br>
  <a href="fileupload.jsp">File Upload</a><br>

  <s:a action="showAjaxLoginForm">Ajax Login</s:a><br>
  <s:set var="mySchemeVar"><s:property value='%{myScheme}'/></s:set>
  <s:if test="myScheme=='https'">
    <s:url var="myUrl" scheme="https" action="showAjaxLoginForm"/>
  </s:if>
  <s:else>
    <s:url var="myUrl" scheme="http" action="jqueryGrid"/>
  </s:else>
  <%--<s:param name="someParam" value="blah"/>--%>
  <%--</s:url>--%>
  <br/>
  <s:set var="myActionVar">showAjaxLoginForm</s:set>
  <s:url var="myUrl2" action="%{#myActionVar}" />
  myUrl2 = <s:property value="%{myUrl2}"/>
  <br>

  <br>
  <s:form id="f1" action="jqueryGrid">
    <s:hidden name="id" value="12"/>
    <s:a id="a1" href="%{myUrl2}">click me</s:a>
    <script type="text/javascript">
      $(document).ready(function() {
        $("#a1").click(function(event) {
          event.preventDefault();
          $("#f1").submit();
        });
      });
    </script>
  </s:form>
  <br/>
  <s:a action="jqueryGrid">JQuery Grid</s:a>
  <br/>
  <s:a action="extjsGrid">ExtJS Grid</s:a>
  <br/>
  <s:a namespace="/modelDriven" action="modelDriven">Model Driven</s:a>
  <br/>
  <%
    for(int i=1;i<=3; i++)
    {
  %>

  <select id='s<%=i%>' onchange="doClicked(this)">
    <option value="Aunt">Aunt</option>
    <option value="Brother">Brother</option>
    <option value="Brother in law">Brother in law</option>
    <option value="Cousin">Cousin</option>
    <option value="Doughter">Doughter</option>
  </select>
  <%
    }
  %>
  <script type="text/javascript">
    var s1 = document.getElementById('s1'), s2 = document.getElementById('s2'), s3 = document.getElementById('s3');
    function doClicked(sel){
      if (sel.id == 's1'){
        if (sel.selectedIndex == s2.selectedIndex || sel.selectedIndex == s3.selectedIndex){
          sel.disabled = true;
          s2.disabled = false;
          s3.disabled = false;
        }
      }
      else if (sel.id == 's2') {
        if (sel.selectedIndex == s1.selectedIndex || sel.selectedIndex == s3.selectedIndex){
          sel.disabled = true;
          s1.disabled = false;
          s3.disabled = false;
        }
      }
      else if (sel == 's3'){
        if (sel.selectedIndex == s1.selectedIndex || sel.selectedIndex == s2.selectedIndex){
          sel.disabled = true;
          s1.disabled = false;
          s2.disabled = false;
        }
      }
    }
  </script>
  <%--<object id="appletId" width="400" height="300" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"--%>
  <%--codebase="http://java.sun.com/products/plugin/autodl/jinstall-1_4-windows-i586.cab#Version=1,4,0,0">--%>

  <%--<param name="code" value="Application"/>--%>
  <%--<param name="archive" value="pleasewait.jar"/>--%>
  <%--<!--[if !IE]> -->--%>
  <%--<object id="appletId" width="400" height="300" type="application/x-java-applet">--%>

  <%--<param name="code" value="Application"/>--%>
  <%--<param name="archive" value="pleasewait.jar"/>--%>
  <%--</object>--%>
  <%--<!-- <![endif]-->--%>
  <%--</object>--%>
  <%--<object id="appletId" type="application/x-java-applet" archive="pleasewait.jar" height="300" width="400">--%>
  <%--<param name="code" value="Application" />--%>
  <%--<param name="archive" value="pleasewait.jar" />--%>
  <%--</object>--%>
  <br>

  <br>
  <s:a id="dialog1" href="javascript:void(0);" tooltip="Choose your favorite dialog" >Dialog</s:a> <br>
  <div id="dialog-form" >
    <form action="" id="channelfinder">
      <label for="products"></label><textarea id="products" name="prodnbr" cols="5" rows="5"><s:property value='prodNbr'/></textarea>
    </form>
  </div>

  <%--<div id="dialog" title="Basic dialog">--%>
  <%--<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>--%>
  <%--</div>--%>
  <br>
  <s:set var="alert" value="'your account is past due'"/>
  <s:if test="#alert.contains('past due')">
    past due, contains<br>
  </s:if>
  <s:if test="#alert.contains('past')">
    past, contains<br>
  </s:if>
  <s:if test="#alert.matches('.*past due.*')">
    past due, matches<br>
  </s:if>
  <br>

  <s:form id="form1" action="datepicker">
    <%--<s:a id="a12" tooltip="Choose datepicker" action="datepicker" ><s:param name="struts.token.name" value="%{tokenName}"/><s:param name="%{tokenName}" value="%{token}"/>Datepicker</s:a> <br>--%>
    <s:a id="a12" tooltip="Choose datepicker" action="datepicker" >Datepicker</s:a> <br>

    <%--<script type="text/javascript">--%>
    <%--$(document).ready(function() {--%>
    <%--$("#a12").click(function(event) {--%>
    <%--event.preventDefault();--%>
    <%--var form = $("#form1");--%>
    <%--form.attr("action",$(this).attr("href"));--%>
    <%--form.submit();--%>
    <%--});--%>
    <%--});--%>
    <%--</script>--%>

    <sj:textfield id="text1" tooltip="Just tooltip"/>
    <s:token name="%{tokenName}"/>
    <s:submit value="Submit token"/>
  </s:form>
  <br>
  <s:if test="%{isItTrue()}">
    It is true<br>
  </s:if>
  <s:else>
    It is not true<br>
  </s:else>
  <br>


  <s:form namespace="/" action="save" method="post" >
    <s:set var="tag1" value="'registrationType'"/>
    <s:select id="RegistrationType" name="type" label="%{getText('label.'+#tag1)}"
              list="{'Student', 'Faculty'}" emptyOption="false" />
    <s:div id="Student">
      <%--<s:textfield label="FirstName" name="firstName"/>--%>
      <%--<s:textfield label="LastName" name="lastName"/>--%>
      <%--<s:textfield label="UserName" name="username"/>--%>
      <%--<s:password label="Password" name="password"/>--%>
      <s:textfield label="Email" name="email"/>
      <s:textfield label="%{getText('label.'+#tag1)}" name="%{#tag1}" />
      <s:submit/>
      <script type="text/javascript">
        $(function(){
          $("input[type='submit']").click(function(){
            var selected = this.form['type'].value;
            alert("Selected " + selected);
            return false;

          });
        });
      </script>

    </s:div>

  </s:form>
  <script type="text/javascript">
    $(document).ready(function(){
      $('#RegistrationType').change(function(){
        alert('#'+this.value);
        $('#'+this.value).css('display', 'block');
        if (this.value=='Student') $('#Faculty').css('display', 'none');
        else $('#Student').css('display', 'none');
      });
    });
  </script>

  <br>
  <script type="text/javascript">
    $(document).ready(function(){
      $('#before').click(function() {
        $('#text').val('Text Changed by jQuery');
      });
    });
  </script>
  <s:div id="result"/>
  <s:form id="form2" namespace="/" action="%{myFormUrl}" method="POST" theme="simple" >
    <s:textfield id="text" name="phone" value="Hello World!!!"/> <br>
    <div  style="display:inline-block;white-space:nowrap;">
        <s:submit  id="before" targets="result" />
        <s:submit  id="after" targets="result" />
    <div>
  </s:form>
  <br>
  <%--<s:set value="%{@com.opensymphony.xwork2.ActionContext@getContext().setLocale(new java.util.Locale('ru'))}"/>--%>
  <%--<s:date name="myDate" format="EEEE, dd MMMM"/>--%>
  <s:property value="%{new java.text.SimpleDateFormat('EEEE, dd MMMM', new java.util.Locale('ru')).format(myDate)}"/>
  <br>
  <s:a id="a12" tooltip="Continue with Select" action="select" >Select</s:a> <br>
  <s:a id="a13" tooltip="Continue with MultiSelect" action="multiselect" >Multi-Select</s:a> <br>
  <s:a id="a12" tooltip="Continue with Autocompleter Select" action="autocompleter-select" >Autocompleter Select</s:a> <br>
  <s:a id="a12" tooltip="Continue with Autocompleter JSON" action="autocompleter-json" >Autocompleter JSON</s:a> <br>
  <br>
  <s:a id="a13" tooltip="Pubsub" action="pubsub" >Pubsub</s:a> <br>
  <br>

  <% String str27 = "hello27"; %>
  <% ActionContext.getContext().getValueStack().set("str27", str27); //set to root %>
  <s:property value="str27"/> <br>
  <% ActionContext.getContext().getValueStack().getContext().put("str27", str27); //set to context %>
  <s:property value="#str27"/> <br>
  <s:set var="str28" value="'str27'"/>  <br>

  struts.valueStack: <s:property value="%{#request['struts.valueStack']}"/> <br>

  str28: <s:property value="%{#request['struts.valueStack'].context[#str28]}"/><br>
  fib(11) = <s:property value="#fib =:[#this==0 ? 0 : #this==1 ? 1 : #fib(#this-2)+#fib(#this-1)], #fib(11)" /><br>

  <s:set var="bar" value="'hello'"/>
  <s:set var="foo" value="'bar'"/>
  foo = <s:property value="%{#attr.bar}"/><br>
  <br>
  <s:set var="list1" value="{1,2}"/>
  <s:set var="list2" value="{3,4}"/>
  <s:select list="%{#list1.addAll(#list2), #list1}" />

  <br>
  <s:form name="myForm3" namespace="/" action="save">
    <%--<s:textfield name="myFloat" value="%{getText('{0,number,#,##0.00}', {myFloat})}"/><br>--%>
    <%--<s:textfield name="myFloat" value="%{myFloat}"/>--%>
    <s:textfield type = "number"
           name = "myFloat"
           min = "0"
           max = "100"
           maxlength = "4"
           step = "0.01"
           lang="%{#context['com.opensymphony.xwork2.ActionContext.locale']}"/>
           <%--value="%{getText('{0,number,#,##0.00}', {#myFloat})}"--%>
    <%--/>--%>
    <s:submit action="test"/> <br>
    <script>
      $("#spinner").spinner({ step: 0.01, numberFormat: "n", culture: "en" });
    </script>
  <s:a action="" onclick="this.href=this.href+'?'+'myFolat='+document.forms['myForm3']['myFloat'].value+'&action:test';alert(this);">Link</s:a>
  </s:form>
  <br>

  <c:set var="actionName"><s:property value="%{#context['struts.actionMapping'].name}"/></c:set>
  action name: ${actionName}<br/>
    <br>
    The action url:<s:url var="actionUrl"/>${actionUrl}
  <br>
  <s:push value="%{#myFormUrl}">
    <s:select name="actionAttribute" list="{'First', 'Second'}"
              onchange="ajaxFunction('%{top}')" />
  <script type="text/javascript">
    function ajaxFunction(url){
      alert(url);
    }
  </script>
  </s:push>
  <br>
  <s:set var="testa">{6,7,8,9,10}</s:set>
  testa: <s:property value="#testa"/><br>

  <s:set var="testb" value="{6,7,8,9,10}"/>
  testb: <s:property value="#testb"/><br>

  <%--<s:set var="testc" value="%{#testa.replaceAll('[\\\{\\\}]','').split(',')}"/>--%>
  <s:set var="testc" value="%{new int[] {2,1}}"/>
  testc: <s:property value="#testc"/><br>
  <s:iterator value="#testc">
    <s:property/><br>
  </s:iterator>  <br>
  It is: <s:property value="itTrue"/>
  <br>
  <s:set var="A" value="true" />
  <s:set var="B" value="false" />
  <s:property value="%{''+#A+#B}"/>
  <br>
  <s:a action="freemarkeraction">Freemarker Action</s:a><br>
  <br>
    <% User user=(User)session.getAttribute("user"); %>
    <%=user.getName() %>
  Username from session:<c:out value="${sessionScope.user.name }"/>
  <br>
  My Set:<br>
  <s:form namespace="/" action="save" theme="simple">

    <s:textfield name="mySet" value="1"/> <br>
    <s:textfield name="mySet" value="2"/> <br>
    <s:textfield name="mySet" value="3"/> <br>

    <br>
    Double converter: <s:textfield name="doubleValue"/>

    <%--<s:submit />--%>
    <s:submit type="button" cssClass="btn btn-primary" key="Modifier" theme="simple">Submit&nbsp;&nbsp;<i class="icon-ok"></i></s:submit>
  </s:form>
  <br>
  <s:a action="load-on-scroll-example">Load on Scroll Example</s:a><br>
  <br>
  <s:set var="oldLocale" value="#context['com.opensymphony.xwork2.ActionContext.locale']" />
  Old Locale: <s:property value="#oldLocale"/><br>
  <s:set var="com.opensymphony.xwork2.ActionContext.locale" value="new java.util.Locale('en')"/>
  Locale: <s:property value="#context['com.opensymphony.xwork2.ActionContext.locale']"/><br>
  English: <s:text name="label.upload"/><br>
  <s:set var="com.opensymphony.xwork2.ActionContext.locale" value="new java.util.Locale('ru')"/>
  Locale: <s:property value="#context['com.opensymphony.xwork2.ActionContext.locale']"/><br>
  Russian: <s:text name="label.upload"/><br>
  <br>
  <s:set var="sidePanelName"></s:set>
  sidePanelName: <s:property value="#sidePanelName"/>
  <br>
  Last form:
  <form>
    <a href="#" onclick="callServlet()" ><img
      src="https://www.paypalobjects.com/webstatic/en_US/btn/btn_buynow_pp_142x27.png"
      alt="PayPal - The safer, easier way to pay online!"></a>
    <script type="text/javascript">
      function callServlet() {
        alert('document.location.href="test-servlet.jsp";');
      }
    </script>

  </form>
  <br>
    <s:set var="key" value="some1"/>
    <s:set var="keyValue" value="some2"/>
    key: <s:property value="key"/><br>
    keyValue: <s:property value="keyValue"/><br>
    <c:import url="include.jsp"/>
    <s:url var="keyUrl" action="pubsub"><s:param name="%{#key}" value="%{#keyValue}"/></s:url>

    2. <s:property value="#keyUrl"/><br>
  <br>
  <h5>Intrface in JSP EL</h5>
    <table>
      <% pageContext.setAttribute("quests", new Questions(){}.QUESTION_ARRAY); %>
      <c:forEach var="question" items="${quests}" varStatus="ctr">
        <tr>
          <td>
            <%=Questions.QUESTION_ARRAY[((LoopTagStatus)pageContext.getAttribute("ctr")).getIndex()]%>
           </td>
           <td>
              ${quests[ctr.index]}
           </td>
          <td>
            ${question}
          </td>
        </tr>
      </c:forEach>
    </table>

  <br>

  <s:action var="bkngorder" name="getBookingorderDeatils"
            executeResult="true">  <%--ignoreContextParams="true"--%>
    <s:param name="foo" value="bar"/>
  </s:action>

  <br>
  Static param:<br>
    <s:url id="save1Url" namespace="/" action='save1' escapeAmp="false">
      <s:param name="par1" value="'66666666666'"/>
    </s:url>
  <s:form namespace="/" action="save1" theme="simple">

    <s:textfield name="par1" value="1"/> <br>

    <%--<s:submit />--%>
    <%--<s:submit type="button" cssClass="btn btn-primary" key="Modifier" theme="simple">Submit&nbsp;&nbsp;<i class="icon-ok"></i></s:submit>--%>
    <s:a href="%{#save1Url}">Call</s:a>
  </s:form>
  <br>
  Parametrized submit
  <br>
  <s:form name="myForm13" namespace="/" action="save?message=Hello param 3" theme="simple">
    <br/><s:a cssClass="btn btn-primary" action="test"><s:param name="message">Hello param 1</s:param>&nbsp;&nbsp;&nbsp;Go&nbsp;&nbsp;&nbsp;</s:a>
    <br/><s:a href="#" cssClass="btn btn-warning" onclick="myForm13.action='test?message=Hello param 2';myForm13.submit()">Submit</s:a>
    <br/><s:submit cssClass="btn btn-danger" action="test"/>

  </s:form>

</body>
<%--&lt;% new SSL().main(null); out.println("ok");%&gt;--%>

</html>


