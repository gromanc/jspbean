<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<head>
  <link href="<s:url value="/css/template_styles.css"/>" type="text/css" rel="stylesheet">
  <sj:head />
  <title>jQuery Grid</title>
  <script type="text/javascript">
    $(document).ready(function(){
    console.log("Before subscribe");
    $("#gridtable").subscribe("beforeTopic", function(topic, data) {

      console.log('Topic: '+data, topic);
      if ( $("#gridtable").isSubscribed("beforeTopic") ){
        console.log('Subscribed: '+data, topic);
        return;
      }
      //go on with function
      console.log('Not subscribed: '+data, topic);
    });
    console.log("After subscribe");
    });
    </script>
</head>
<body>
   <div>
     <s:url var="editurl" value="editTable" />
     <s:url var="remoteurl" action="jsonTable" />
     <s:url var="echoUrl" action="echo" />
     <script type="text/javascript">
        function formatLink(cellvalue, options, rowObject) {
          return "<a href='<s:property value="echoUrl" />?id="+rowObject["id"]+"' onClick='javascript: return doClick(this, "+rowObject.id+")'>" + cellvalue + "</a>";
        }
        function unformatLink( cellvalue, options, cell){
          return $('a', cell).text();
        }
        function doClick(e, id) {
          $.get(e.href, {echo : id}).done(function(data){
            alert(data);
          });
          return false;
        }
     </script>
     <sjg:grid id="gridtable" caption="Subject Setup Navigator"

         dataType="json" href="%{remoteurl}" pager="true" rowList="10,20,50"

         rowNum="5" navigator="true" width="900"
         navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"

         navigatorAddOptions="{height:280,reloadAfterSubmit:false}"

         navigatorEditOptions="{height:280,reloadAfterSubmit:true}"

         navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"

         navigatorEdit="true" navigatorAdd="false" navigatorView="true"

         navigatorDelete="true" gridModel="gridModel" editurl="%{editurl}"

         editinline="true" rownumbers="true"
         onBeforeTopics="beforeTopic"
         onGridCompleteTopics="gridcomplete"
         >
         <%--viewrecords="true"--%>
        	<%--viewsortcols="[true, 'horizontal', true]"--%>


         <sjg:gridColumn name="id" index="id" title="id"
                             		search="true"
                             		searchoptions="{sopt:['eq','ne','lt','gt']}"
             formatter="integer" sortable="true" key="true" hidden="" />


         <sjg:gridColumn name="name" index="name"
             editrules="false" title="name" sortable="true" search="true"
             editable="true" edittype="text" formatter = "formatLink" />
     </sjg:grid>
     <script type="text/javascript">
      $(document).ready(function(){
        console.log('Subscribed='+$("#gridtable").isSubscribed("beforeTopic"));
        $("#gridtable").jqGrid('setColProp', 'name',{
          unformat: unformatLink
        }).trigger("reloadGrid");
      });
     </script>
   </div>

  <%--<div>--%>
    <%--<sjg:grid--%>
        <%--id="gridtable"--%>
        <%--caption="Example (Editable/Multiselect)"--%>
        <%--dataType="local"--%>
        <%--pager="true"--%>
        <%--navigator="true"--%>
        <%--navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"--%>
        <%--navigatorAddOptions="{height:280, width:500, reloadAfterSubmit:true}"--%>
        <%--navigatorEditOptions="{height:280, width:500, reloadAfterSubmit:false}"--%>
        <%--navigatorEdit="true"--%>
        <%--navigatorView="true"--%>
        <%--navigatorViewOptions="{height:280, width:500}"--%>
        <%--navigatorDelete="true"--%>
        <%--navigatorDeleteOptions="{height:280, width:500,reloadAfterSubmit:true}"--%>
        <%--gridModel="gridModel"--%>
        <%--rowList="5,10,15"--%>
        <%--rowNum="5"--%>
        <%--rownumbers="true"--%>
        <%--editurl="%{editurl}"--%>
        <%--editinline="true"--%>
        <%--multiselect="true"--%>
        <%--onSelectRowTopics="rowselect"--%>
        <%-->--%>

        <%--<sjg:gridColumn name="id" index="id" title="Id" formatter="integer" editable="false" sortable="true" search="true" sorttype="integer" searchoptions="{sopt:['eq','ne','lt','gt']}"/>--%>
        <%--<sjg:gridColumn name="name" index="name" key="true" title="Country Name" editable="true" edittype="text" sortable="true" search="true" sorttype="text"/>--%>

    <%--</sjg:grid>--%>
    <%--<script type="text/javascript">--%>
      <%--$(document).ready(function(){--%>
        <%--var mydata = [{id:"1",name:"Ivan Petrov"}];--%>
<%--//        for(var i=0;i<=mydata.length;i++) $("#gridtable").jqGrid('addRowData',i+1,mydata[i]);--%>
        <%--$("#gridtable").jqGrid('setGridParam', {--%>
            <%--data: mydata--%>
        <%--}).trigger("reloadGrid");--%>
      <%--});--%>
    <%--</script>--%>
  <%--</div>--%>
<br>
   <s:url var="remoteurlModelDriven" action="jsonTableModelDriven" />
   <s:url var="editurlModelDriven" value="editTableModelDriven" />
   <sjg:grid id="gridtableModelDriven" caption="Subject Setup Navigator ModelDriven"

       dataType="json" href="%{remoteurlModelDriven}" pager="true" rowList="10,20,50"

       rowNum="5" navigator="true" width="900"
       navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"

       navigatorAddOptions="{height:280,reloadAfterSubmit:false}"

       navigatorEditOptions="{height:280,reloadAfterSubmit:true}"

       navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"

       navigatorEdit="true" navigatorAdd="false" navigatorView="true"

       navigatorDelete="true" gridModel="gridModel" editurl="%{editurlModelDriven}"

       editinline="true" rownumbers="true"

       >
       <%--viewrecords="true"--%>
      	<%--viewsortcols="[true, 'horizontal', true]"--%>


       <sjg:gridColumn name="id" index="id" title="id"
                           		search="true"
                           		searchoptions="{sopt:['eq','ne','lt','gt']}"
           formatter="integer" sortable="true" key="true" hidden="" />


       <sjg:gridColumn name="name" index="name"
           editrules="false" title="name" sortable="true" search="true"
           editable="true" edittype="text" formatter="formatLink"/>
   </sjg:grid>
 </div>

</body>
</html>