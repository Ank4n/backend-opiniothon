package com.backend.opiniothon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import response.LoginResponse;

@RestController
public class AppController {

	private UserRepo userRepo;

	@Autowired
	public AppController(UserRepo userRepo) {
		this.userRepo = userRepo;
		init();
	}

	private void init() {
		UserEntity deliveryGuy = new UserEntity("delivery", "1111", UserEntity.USER_TYPE_DELIVERY_AGENT);
		UserEntity consumer = new UserEntity("consumer", "1111", UserEntity.USER_TYPE_CONSUMER);
		userRepo.save(deliveryGuy);
		userRepo.save(consumer);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse login(@RequestBody UserEntity user) {
		List<UserEntity> dbUsers = userRepo.findByUserName(user.getUserName());
		UserEntity dbUser = null;
		if (!dbUsers.isEmpty())
			dbUser = dbUsers.get(0);
		
		if (dbUser != null) {
			System.out.println("Username:" + dbUser.getUserName() + "Password:" + dbUser.getPassword());
			if (dbUser.getPassword().equals(user.getPassword())) {
				return new LoginResponse("success", dbUser.getType());
			}

		}

		return new LoginResponse("email or password is incorrect", null);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody UserEntity user) {
		user = userRepo.save(user);
		System.out.println("Adding new user with Username:" + user.getUserName() + "Password:" + user.getPassword() + " and id:" + user.getId());
		
	}

	
}
