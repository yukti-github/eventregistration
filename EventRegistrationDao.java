package com.hsbc.banking.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.banking.models.EventRegistration;

public interface EventRegistrationDao {

	boolean addEventRegistration(EventRegistration eventRegistration)throws SQLException;
	List<EventRegistration> getEventRegistrations()throws SQLException;
	int countEventRegistrationByEventId(long eventId)throws SQLException;
	int getSeatNo(long eventId)throws SQLException;
	
	
}
