package com.hsbc.banking.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.banking.dao.implementations.PersonImpl;
import com.hsbc.banking.dao.interfaces.PersonDao;
import com.hsbc.banking.models.Person;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mobileNo=request.getParameter("mobileNo");
		String name=request.getParameter("name");
		//invoke the model
		Person person=new Person();
		person.setMobileNo(Long.parseLong(mobileNo));
		person.setName(name);
		//call dao
		PersonDao dao=new PersonImpl();
		boolean status=false;
		String message=null;
		try {
			status=dao.addPerson(person);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		}

		if(status)
		{
			message="Record Added";
		}
		
		request.setAttribute("message", message);

		request.getRequestDispatcher("views/addPerson.jsp").forward(request, response);
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	List<Person> personList=null;
	//invoke dao layer
	PersonDao dao =new PersonImpl();
	String message=null;
	try {
		personList=dao.getAllPerson();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=e.getMessage();
	}
	
	if(personList!=null)
		request.setAttribute("persons", personList);
	else
		request.setAttribute("persons", message);
	
	request.getRequestDispatcher("views/viewPerson.jsp").forward(request, response);
    	
	
	}
	
	
	
	
	

}
