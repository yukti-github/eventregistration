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

<a href="EventServlet">View Event</a>
<form method="post" action="EventServlet">
<fieldset>
<legend>Add Event</legend>
<input type="number" name="eventId" placeholder="Event Id">
<input type="text" name="eventName" placeholder="Event Name">
<input type="date" name="eventDate" placeholder="Event Date">
<input type="time" name="eventTime" placeholder="Event Time">
<input type="number" name="capacity" placeholder="Capacity">
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