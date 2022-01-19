package com.restful.webservice.user;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
public class UserController {
	
	@Autowired(required = true)
	 private UserDao userDao;
	
	@Autowired
	private UserRepositary userRepositary;
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers(){
		return userRepositary.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable Integer id){
		Optional<User> user=userRepositary.findById(id);
		if(!user.isPresent())
				throw new UserNotFoundException("Id- "+ id);
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUser= linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUser.withRel("all-user"));
		return model;
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		 userRepositary.deleteById(id);
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> SaveUser(@Valid @RequestBody User user){
		User saveuser = userRepositary.save(user);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(saveuser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	

}
