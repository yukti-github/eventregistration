package com.hsbc.banking.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hsbc.banking.dao.interfaces.PersonDao;
import com.hsbc.banking.helpers.DerbyHelper;
import com.hsbc.banking.models.Person;

public class PersonImpl implements PersonDao{

	private Connection conn;
	private PreparedStatement pre;
	private Statement statement;
	private ResultSet resultSet;
	private boolean status;
	private ResourceBundle rb;
	//instance block
	{
		rb =ResourceBundle.getBundle("com/hsbc/"
				+ "banking/resources/db");
	}
	
	
	@Override
	public boolean addPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("addperson");		
		int result=0;
		try
		{
		conn=DerbyHelper.getConnection();
		//transaction
		conn.setAutoCommit(false);
		pre=conn.prepareStatement(query);
		pre.setLong(1, person.getMobileNo());
		pre.setString(2, person.getName());
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
	public Person getPersonById(long mobileNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAllPerson() throws SQLException {
		// TODO Auto-generated method stub
		String query=rb.getString("getallperson");
		List<Person> personList=new ArrayList<Person>();
		Person person=null;
		try
		{
		conn=DerbyHelper.getConnection();
		statement=conn.createStatement();
		resultSet=statement.executeQuery(query);
		
		while(resultSet.next())
		{
			person=new Person();
			person.setMobileNo(resultSet.getLong(1));
			person.setName(resultSet.getString(2));
			personList.add(person);			
		}
		
		}
		catch(SQLException exception)
		{
			
		}
		finally
		{
			conn.close();
		}
		
		
		return personList;
	}

}
