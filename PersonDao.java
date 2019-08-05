package com.hsbc.banking.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.banking.models.Person;

public interface PersonDao {

	boolean addPerson(Person person)throws SQLException;
	Person getPersonById(long mobileNo)throws SQLException;
	List<Person> getAllPerson()throws SQLException;
	
}
