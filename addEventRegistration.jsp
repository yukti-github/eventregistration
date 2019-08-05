<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  import="java.util.Hashtable,java.util.List,com.hsbc.banking.models.Event,com.hsbc.banking.models.Person"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
if(request.getAttribute("data")!=null)
{
	Hashtable<String,List> ht=(Hashtable<String,List>)request.getAttribute("data");
	Event event=null;
	Person person=null;
	%>
	
	<form method="post" action="EventRegistrationServlet">
   
       
		<select name="eventId">
		<%
         for(Object object:ht.get("events"))
         {
            event=(Event)object;  
       %> 
       
          <option value="<%=event.getEventId() %>"><%=event.getName() %></option>
       
       <%
         }
       %>
		</select>
		<br/>
		<select name="personId">
		<%
         for(Object object:ht.get("persons"))
         {
            person=(Person)object;  
       %> 
       
          <option value="<%=person.getMobileNo() %>"><%=person.getName() %></option>
       
       <%
         }
       %>
		
		</select>
		<br/>
<input type="submit" value="Submit">
</form>
	
	
	<% 
	
}


%>
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