package com.hsbc.banking.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.banking.dao.implementations.EventImpl;
import com.hsbc.banking.dao.implementations.PersonImpl;
import com.hsbc.banking.dao.interfaces.EventDao;
import com.hsbc.banking.dao.interfaces.PersonDao;
import com.hsbc.banking.models.Event;
import com.hsbc.banking.models.Person;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//model
	    Event  event=new Event();
	    event.setEventId(Long.parseLong(request.getParameter("eventId")));
	    event.setName(request.getParameter("eventName"));
	    DateTimeFormatter formatter=DateTimeFormatter.ISO_DATE;	    
	    event.setEventDate(LocalDate.parse(request.getParameter("eventDate"),
	    		formatter));	    
	    event.setEventTime(Time.valueOf(request.getParameter
			   ("eventTime").toString()));	
	    event.setCapacity(Integer.parseInt(request.getParameter("capacity")));
	    
	    //dao layer
	    EventDao dao=new EventImpl();
	    boolean status=false;
	    String message=null;
	    try {
			status=dao.addEvent(event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		}
	    
	    if(status)
	    	message="Event Added";
	    
	    request.setAttribute("message", message);

	    request.getRequestDispatcher("views/addEvent.jsp").forward(request, response);
	    
	    
	    
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Event> eventList=null;
		//invoke dao layer
		EventDao dao =new EventImpl();
		String message=null;
		try {
			eventList=dao.getAllEvents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		}
		
		if(eventList!=null)
			request.setAttribute("events", eventList);
		else
			request.setAttribute("events", message);
		
		request.getRequestDispatcher("views/viewEvent.jsp").forward(request, response);
	    	
		
		}

}
