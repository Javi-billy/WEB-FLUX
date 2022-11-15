package com.cqrs.pattern.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.pattern.cqrs.commands.CreateUserCommand;
import com.cqrs.pattern.cqrs.commands.UpdateUserCommand;
import com.cqrs.pattern.cqrs.projections.UserProjection;
import com.cqrs.pattern.cqrs.queries.AddressByRegionQuery;
import com.cqrs.pattern.cqrs.queries.ContactByTypeQuery;
import com.cqrs.pattern.cqrs.repository.UserReadRepository;
import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.es.events.Event;
import com.cqrs.pattern.es.repository.EventStore;
import com.cqrs.pattern.escqrs.aggregates.UserAggregate;
import com.cqrs.pattern.escqrs.projectors.UserProjector;

@RestController
public class EscqrsController {	
	
	private EventStore repository = new EventStore();
	private UserReadRepository urr = new UserReadRepository();	
	private UserProjector up = new UserProjector(urr);
	
	@PostMapping(value = "/createUser-escqrs")
	public String createAggComd(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(repository);
		List<Event> list = ua.handleCreateUserCommand(new CreateUserCommand(idUser,"prueba","otro"));
			
		up.project(idUser, list);
		
		return list.get(0).id.toString();
	}
	
	@PutMapping(value = "/updateUser-escqrs")
	public List<Event> updateAggComd(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(repository);
		
		Set<Contact> contacts = new HashSet<>();
		Set<Address> addresses = new HashSet<>();
		
		Contact contact = new Contact("T","647412588");
		contacts.add(contact);
		Address address = new Address("Madrid","Espania","28016"); 
		addresses.add(address);		
		
		List<Event> list = ua.handleUpdateUserCommand(new UpdateUserCommand(idUser,addresses,contacts));
		up.project(idUser, list);
		return list;
	}	
	
	@GetMapping(value = "/addresses-escqrs/{id}")
	public Set<Address> readAddress(@PathVariable(value = "id") String id) throws Exception {
		UserProjection upr = new UserProjection(urr); 
		return upr.handle(new AddressByRegionQuery(id, "Espania"));
	}
	
	@GetMapping(value = "/contacts-escqrs/{id}")
	public Set<Contact> readContacts(@PathVariable(value = "id") String id) throws Exception {
		UserProjection upr = new UserProjection(urr);
		return upr.handle(new ContactByTypeQuery(id, "T"));
	}	
}
