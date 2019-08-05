package com.hsbc.banking.dao.implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hsbc.banking.dao.interfaces.EventDao;
import com.hsbc.banking.helpers.DerbyHelper;
import com.hsbc.banking.models.Event;
import com.hsbc.banking.models.Person;

public class EventImpl implements EventDao{
      
	private Connection conn;
	private PreparedStatement pre;
	private boolean status;
	private Statement statement;
	private ResultSet resultSet;
	private ResourceBundle rb;
	
	{
		
		rb =ResourceBundle.getBundle("com/hsbc/"
				+ "banking/resources/db");
		
	}
	
	
	@Override
	public boolean addEvent(Event event) throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("addevent");
		int result=0;
		try
		{
		conn=DerbyHelper.getConnection();
		//transaction
		conn.setAutoCommit(false);
		pre=conn.prepareStatement(query);
		pre.setLong(1, event.getEventId());
		pre.setString(2, event.getName());
		pre.setDate(3, Date.valueOf(event.getEventDate()));
		pre.setTime(4, event.getEventTime());
		pre.setInt(5, event.getCapacity());
		result=pre.executeUpdate();
		if(result>0)
			status=true;
		conn.commit();
		
		}
		catch(SQLException exception)
		{
			conn.rollback();
		}
		finally
		{
			conn.close();
		}
		
		
		return status;
	}

	@Override
	public Event getEventById(long eventId) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String query=rb.getString("geteventbyid");
				
				Event event=null;
				try
				{
				conn=DerbyHelper.getConnection();
				pre=conn.prepareStatement(query);
				pre.setLong(1, eventId);
				resultSet=pre.executeQuery();
				if(resultSet.next())
				{
					event=new Event();
					event.setEventId(resultSet.getLong(1));
					event.setName(resultSet.getString(2));
					event.setEventDate(resultSet.getDate(3).toLocalDate());
					event.setEventTime(resultSet.getTime(4));
					event.setCapacity(resultSet.getInt(5));
				}
				
				}
				catch(SQLException exception)
				{
					
				}
				finally
				{
					conn.close();
				}
				
				
				return event;
	}

	@Override
	public List<Event> getAllEvents() throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("getallevents");
		List<Event> eventList=new ArrayList<Event>();
		Event event=null;
		try
		{
		conn=DerbyHelper.getConnection();
		statement=conn.createStatement();
		resultSet=statement.executeQuery(query);
		
		while(resultSet.next())
		{
			event=new Event();
			event.setEventId(resultSet.getLong(1));
			event.setName(resultSet.getString(2));
			event.setEventDate(resultSet.getDate(3).toLocalDate());
			event.setEventTime(resultSet.getTime(4));
			event.setCapacity(resultSet.getInt(5));
			eventList.add(event);
		}
		
		}
		catch(SQLException exception)
		{
			
		}
		finally
		{
			conn.close();
		}
		
		
		return eventList;
	}

}
