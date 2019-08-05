<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--Action tag --%>
<jsp:include page="header.jsp"></jsp:include>

<a href="PersonServlet">View Person</a>

<form method="post" action="PersonServlet">
<fieldset>
<legend>Add Person</legend>
<input type="number" name="mobileNo" placeholder="Mobile No">
<input type="text" name="name" placeholder="Name">
<input type="submit" value="submit">
</fieldset>
</form>
<%--scriplets --%>

<%
String result=null;
if(request.getAttribute("message")!=null)
{
	
	result=request.getAttribute("message").toString();
	%>
	
	<h4><%=result %></h4>
	
	<% 
}

%>



<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>