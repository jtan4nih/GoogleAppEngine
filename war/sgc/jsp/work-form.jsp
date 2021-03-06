<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.appspot.cloudserviceapi.common.model.Constants" %>
<%@page import="tapp.model.sgc.WorkOrder" %>
<%@page import="com.appspot.cloudserviceapi.common.model.StringUtil" %>
<%@page import="com.appspot.cloudserviceapi.common.model.Constants" %>
<%@page import="com.appspot.cloudserviceapi.sgc.dao.EmployeeDAO" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Work Form</title>
  </head>

  <body>
<div data-role="page" data-theme="c">
	<div data-role="header" data-nobackbtn="true">
		<a href="<%= Constants.MAIN_URL %>" data-icon="back"><%=session.getAttribute("data-icon-back")%></a>
		<h1>Work Form</h1> 
	</div>
	<div data-role="content" data-theme="c">
<%
String userID = (String)request.getAttribute("userID");
if(userID != null && userID.equals(Constants.NOT_LOGGED_IN_TITLE)) {
%>
	You are logged in as <%= Constants.NOT_LOGGED_IN_TITLE %> and will not be able to submit work completion checklist until you sign in as an employee.<p>
<%
} else 
if(userID != null && !(new EmployeeDAO()).isUserIdEmployee(userID)) {
%>
	You are logged in as <%= userID %> which is not an employee id. You will not be able to submit work completion checklist until you sign in as an employee.<p>
<% 
} else {
%>
	You are logged in as <%= userID %> and update will be logged under this account.<p>
<div align="center">

<!--  begin - generated by jot form -->
<!-- replace action="http://www.jotform.com/submit.php" with 
action="/sgc/work"
-->

<jsp:include page="../html/pc/work-form.html"/>
<!--  end - generated by jot form -->
<% 
}
%>

	</div>
</div>
  </body>
</html>
