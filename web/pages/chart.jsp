<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 05.11.2014
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<html>
<head>
  <title></title>
  <%--<script type="text/javascript" src="https://www.google.com/jsapi"></script>--%>
  <%--<script type="text/javascript" src="<c:url value='/js/chart.js'/>"></script>--%>
  <s:url var="contextPath" value="/" />
  <sj:head debug="true" compressed="false" loadAtOnce="true" scriptPath="%{#contextPath}template/"/>
  <%--<script src="${contextPath}template/js/struts2/jquery.chart.struts2.js"></script>--%>
  <style type="text/css">
    .demo-placeholder {
      width: 100%;
      height: 100%;
      font-size: 14px;
      line-height: 1.2em;
    }
  </style>
  <%--<script language="javascript" type="text/javascript" src="${contextPath}template/js/flot/jquery.flot.js"></script>--%>
  <%--<script language="javascript" type="text/javascript" src="${contextPath}template/js/flot/jquery.flot.categories.js"></script>--%>

</head>
<body>
<%--<div class="ui segment">--%>
  <%--<div class="ui top attached label">profits</div>--%>
  <%--<div id="chart_div" style="width: 900px; height: 500px;"></div>--%>
<%--</div>--%>
<p>
  jQuery Chart
</p>

<%--<div id="placeholder" class="demo-placeholder"></div>--%>
<sjc:chart id="placeholder" cssClass="demo-placeholder"/>
<%--<sjc:chart--%>
    <%--id="placeholder"--%>
    <%--cssStyle="width: 600px; height: 400px;">--%>
    <%--pie="true"--%>
    <%--pieLabel="true" --%>

<%--</sjc:chart>--%>
<script>
  /*
    var chartData= [
      { label: "Chrome",  data: 36.3, color: "#89A54E"},
      { label: "Other",  data: 0.8, color: "#3D96AE"}
    ];
    var series =  { pie: { show: true }};
    var o = {};
    o.data = chartData;
    o.series= series;
    $.struts2_jquery_chart.chart($("#placeholder"), o);
    */

  $(function(){

    var chartData= [[ ["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9] ]];

  var o = {
    series: {
      bars: {
        show: true,
        barWidth: 0.6,
        align: "center"
      }
    },
    xaxis: {
      mode: "categories",
      tickLength: 0
    }
  };
  o.data = chartData;
  $.struts2_jquery_chart.chart($("#placeholder"), o);
//    $.plot("#placeholder", [chartData], o);
  });

</script>
</body>
</html>
