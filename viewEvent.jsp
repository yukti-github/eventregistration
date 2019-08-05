<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   import="com.hsbc.banking.models.Event,java.util.List"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--Action tag --%>
<jsp:include page="header.jsp"></jsp:include>


 <table> 
<%
  
   
   if(request.getAttribute("events")!=null)
   {
	   
	   for(Event event: (List<Event>)request.getAttribute("events"))
	   {
		 %>
		 <tr>
		 <td><%=event.getName() %></td>
		 <td><%=event.getEventDate() %></td>
		 <td><%=event.getEventTime() %></td>
		 <td><%=event.getCapacity() %></td>
		 </tr>		 
		 
		 <%  		   
	   }	   
	   
   }


%>
</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>