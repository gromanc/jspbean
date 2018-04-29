<%@ page contentType="text/html;charset=windows-1251" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    request.setAttribute("decorator", "none");
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<script type="text/javascript">
  doCloseAuth();
  document.location.reload(true);
</script>

<p align="center"><font color="#000080" size="5">Login Successful!</font></p>
<p> Welcome, <%=request.getParameter("username")%>  </p>
