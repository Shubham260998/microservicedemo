package com.restful.webservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	
	private static List<User> users= new ArrayList<User>();
	private static int UserCount=3;
	
//	static {
//		users.add(new User(1,"Adam", new Date()));
//		users.add(new User(2,"Rossy", new Date()));
//		users.add(new User(3,"Lovely", new Date()));
//	}

	public List<User> findAll(){
		return users;
		
	}
	public User save(User user) {
		if(user.getId()== null) {
			user.setId(UserCount++);
		}
		users.add(user);
		return user;	
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId()== id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		for(User user: users) {
			if(user.getId()== id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
}
