package com.restful.webservice.post;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.restful.webservice.user.User;
import com.restful.webservice.user.UserNotFoundException;
import com.restful.webservice.user.UserRepositary;

@RestController
public class PostController {
	
	@Autowired
	private PostRepositary postrepositary;
	
	@Autowired
	private UserRepositary userRepositary;
	
	@GetMapping(path="/post/{id}")
	public List<Post> retriveAllPost(@PathVariable Integer id){
		
		Optional<User> user = userRepositary.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User is not present with this Id"+id);
			return user.get().getPost();
	}
	
//	@GetMapping(path="/users/{id}")
//	public EntityModel<User> retriveUserById(@PathVariable Integer id){
//		Optional<User> user=userRepositary.findById(id);
//		if(!user.isPresent())
//				throw new UserNotFoundException("Id- "+ id);
//		EntityModel<User> model = EntityModel.of(user.get());
//		//WebMvcLinkBuilder linkToUser= linkTo(methodOn(this.getClass()).retriveAllUsers());
//		//model.add(linkToUser.withRel("all-user"));
//		return model;
//	}
//	
//	@DeleteMapping(path="/users/{id}")
//	public void deleteUserById(@PathVariable Integer id) {
//		 userRepositary.deleteById(id);
//	}
//	
	@PostMapping(path="/post/{id}")
	public ResponseEntity<Object> createPostForUser(@Valid
			@RequestBody Post post, @PathVariable Integer id){
		
		Optional<User> saveuser = userRepositary.findById(id);
		
		if(!saveuser.isPresent())
		throw new UserNotFoundException("User is not present with this Id"+id);
		User user= saveuser.get();
		post.setUser(user);
		postrepositary.save(post);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

}
