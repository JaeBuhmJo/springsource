package com.spring.service;

import java.util.List;

import com.spring.domain.PersonDTO;

public interface PersonService {
	boolean insertPerson(PersonDTO insert);
	boolean updatePerson(PersonDTO insert);
	boolean deletePerson(String id);
	PersonDTO getRow(String id);
	List<PersonDTO> getRows();
	
}
