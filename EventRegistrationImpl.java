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
import com.hsbc.banking.dao.interfaces.EventRegistrationDao;
import com.hsbc.banking.helpers.DerbyHelper;
import com.hsbc.banking.models.Event;
import com.hsbc.banking.models.EventRegistration;

public class EventRegistrationImpl implements EventRegistrationDao{

	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement pre;
	private boolean status;
	private ResourceBundle rb;
	
	{
		rb =ResourceBundle.getBundle("com/hsbc/"
				+ "banking/resources/db");
	}
	
	
	@Override
	public boolean addEventRegistration(EventRegistration eventRegistration) throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("addreg");
		int result=0;
		EventDao dao=new EventImpl();
		
		if(countEventRegistrationByEventId(eventRegistration.getEventId())<(dao.getEventById(eventRegistration.getEventId()).getCapacity()))
		{
			try
			{
			conn=DerbyHelper.getConnection();
			
			
			//transaction
			conn.setAutoCommit(false);
			pre=conn.prepareStatement(query);
			pre.setLong(1, eventRegistration.getEventId());
			pre.setLong(2, eventRegistration.getMobileNo());
			pre.setInt(3, eventRegistration.getSeatNo());
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
			
		}
		
		
		
		return status;
	}

	@Override
	public List<EventRegistration> getEventRegistrations() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countEventRegistrationByEventId(long eventId) throws SQLException{
		// TODO Auto-generated method stub
		String query=rb.getString("getseatcount");
		int seatCount=0;
		try
		{
		conn=DerbyHelper.getConnection();
		pre=conn.prepareStatement(query);
		pre.setLong(1, eventId);
		
		resultSet=pre.executeQuery();

		if(resultSet.next())
		{
			seatCount=resultSet.getInt(1);
		}
		
		
		}
		catch(SQLException exception)
		{
			
		}
		finally
		{
			conn.close();
		}
		
		
		return seatCount;
	}

	@Override
	public int getSeatNo(long eventId) throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("getseatno");
		int seatNo=0;
		try
		{
		conn=DerbyHelper.getConnection();
		pre=conn.prepareStatement(query);
		pre.setLong(1, eventId);
		
		resultSet=pre.executeQuery();

		if(resultSet.next())
		{
			seatNo=resultSet.getInt(1);
		}
		
		
		}
		catch(SQLException exception)
		{
			
		}
		finally
		{
			conn.close();
		}
		
		
		return seatNo;
	}

}
