package com.cqrs.pattern.es.controller;

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
import com.cqrs.pattern.cqrs.repository.UserReadRepository;
import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.domain.User;
import com.cqrs.pattern.es.events.Event;
import com.cqrs.pattern.es.repository.EventStore;
import com.cqrs.pattern.es.service.UserService;
import com.cqrs.pattern.escqrs.aggregates.UserAggregate;
import com.cqrs.pattern.escqrs.projectors.UserProjector;

@RestController
public class CqrsController {	
	
	//private UserService us = new UserService(new EventStore()); 
	//private UserProjector up = new UserProjector( new UserReadRepository());
	
	private EventStore repository = new EventStore(); 
	private UserReadRepository readRepository = new UserReadRepository();
	
	@GetMapping(value = "/home")
	public String hello() {
		return "Welcome to CQRS";
	}
	
	@PostMapping(value = "/createAggComd")
	public String createAggComd(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(repository);
		List<Event> list = ua.handleCreateUserCommand(new CreateUserCommand(idUser,"prueba","otro"));
		
		return list.get(0).id.toString();
	}
	
	@PutMapping(value = "/updateAggComd")
	public List<Event> updateAggComd(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(repository);
		
		Set<Contact> contacts = new HashSet<>();
		Set<Address> addresses = new HashSet<>();
		
		Contact contact = new Contact("T","647412588");
		contacts.add(contact);
		Address address = new Address("Madrid","Espania","28016"); 
		addresses.add(address);		
		
		List<Event> list = ua.handleUpdateUserCommand(new UpdateUserCommand(idUser,addresses,contacts));
		
		return list;
	}
	
	@GetMapping(value = "/read-user/{id}")
	public Set<Address> readUserProjQy(@PathVariable(value = "id") String id) throws Exception {
		UserProjector up = new UserProjector(readRepository);
		
		
		return null;
	}
	
	/*@PostMapping(value = "/create")
	public User create(@RequestBody String idUser) throws Exception {
		
		
		Set<Contact> contacts = new HashSet<>();
		Set<Address> addresses = new HashSet<>();
		
		Contact contact = new Contact("T","647412588");
		contacts.add(contact);
		Address address = new Address("Madrid","Espania","28016"); 
		addresses.add(address);		
		//String idUser = UUID.randomUUID().toString();
		User user = new User(idUser,"prueba","otro",contacts,addresses);
		
		//UserService us = new UserService(new EventStore()); 
		us.createUser(idUser,"prueba","otro");
		us.updateUser(idUser, contacts, addresses);
        System.out.println("usuario creado");        
        up.project(user);
		return user;
	}
	
	@GetMapping(value = "/read-user/{id}")
	public Set<Address> readUser(@PathVariable(value = "id") String id) throws Exception {
		Set<Address> addresses = us.getAddressByRegion(id, "Espania");
		return addresses;
	}*/
	
}
