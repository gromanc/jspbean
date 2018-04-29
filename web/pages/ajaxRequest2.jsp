<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<script>
  $.ajax({

    url:"../pages/ajaxResult2.jsp",
    success: function(returndata){
      alert(returndata);
    }
  });
</script>

<script language="javascript">

  function raj(){

      <%String contact; if ((String contact=request.getParameter("contact")) != null) %>{

      <% String str=request.getParameter("video"); %>
      var s="<%=str%>";
      alert(s);
      <%}%>
    }

</script>