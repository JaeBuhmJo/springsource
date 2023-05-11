package com.spring.service;

import java.util.List;

import com.spring.domain.PersonDTO;

public interface PersonService {
	boolean insertPerson(PersonDTO insert);
	boolean updatePerson(PersonDTO insert);
	boolean deletePerson(String id);
	List<PersonDTO> getRows();
	PersonDTO getRow(String id);
	
}
