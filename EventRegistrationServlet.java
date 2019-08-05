package com.hsbc.banking.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.banking.dao.implementations.EventImpl;
import com.hsbc.banking.dao.implementations.EventRegistrationImpl;
import com.hsbc.banking.dao.implementations.PersonImpl;
import com.hsbc.banking.dao.interfaces.EventDao;
import com.hsbc.banking.dao.interfaces.EventRegistrationDao;
import com.hsbc.banking.dao.interfaces.PersonDao;
import com.hsbc.banking.models.Event;
import com.hsbc.banking.models.EventRegistration;
import com.hsbc.banking.models.Person;

/**
 * Servlet implementation class EventRegistrationServlet
 */
@WebServlet("/EventRegistrationServlet")
public class EventRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String eventId=request.getParameter("eventId");
		String personId=request.getParameter("personId");
		System.out.print(eventId+personId);
		//invoke dao
		EventRegistrationDao dao =new EventRegistrationImpl();
		int seatNo=0;
		//model
		EventRegistration eventRegistration=new EventRegistration();
		boolean status=false;
		String message=null;
		eventRegistration.setEventId(Long.parseLong(eventId));
		eventRegistration.setMobileNo(Long.parseLong(personId));
		try {
			eventRegistration.setSeatNo(dao.getSeatNo(Long.parseLong(eventId))+1);
			status=dao.addEventRegistration(eventRegistration);
			
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			message=e.getMessage();
		}
		
		if(status)
			message="Added User to Event";
		request.setAttribute("message", message);
		request.getRequestDispatcher("views/addEventRegistration.jsp").forward(request, response);
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//invoke Person Dao
		PersonDao personDao=new PersonImpl();
		EventDao eventDao=new EventImpl();
		List<Person> personList=null;
		List<Event>  eventList=null;
 		
		try {
			personList=personDao.getAllPerson();
			eventList=eventDao.getAllEvents();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hashtable<String,List> ht=new Hashtable<String, List>();
		ht.put("persons", personList);
		ht.put("events",eventList);
		
		if((personList!=null)&&(eventList!=null))
			request.setAttribute("data", ht);
		else
			request.setAttribute("data", "No Data");
		
		request.getRequestDispatcher("views/addEventRegistration.jsp").forward(request, response);
		
		
	}
}
