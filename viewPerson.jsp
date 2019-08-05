<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   import="com.hsbc.banking.models.Person,java.util.List"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
 <table> 
<%
  
   
   if(request.getAttribute("persons")!=null)
   {
	   
	   for(Person person: (List<Person>)request.getAttribute("persons"))
	   {
		 %>
		 <tr>
		 <td><%=person.getMobileNo() %></td>
		 <td><%=person.getName() %></td>
		 </tr>		 
		 
		 <%  		   
	   }	   
	   
   }


%>
</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>