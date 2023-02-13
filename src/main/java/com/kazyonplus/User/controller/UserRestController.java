package com.kazyonplus.User.controller;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kazyonplus.User.model.User;
import com.kazyonplus.User.model.UserRequest;
import com.kazyonplus.User.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class UserRestController {

	@Autowired
	private final UserService userService;
	@Autowired
	private final PasswordEncoder passwordEncoder;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')or hasRole('ROLE_CONTRACT')or hasRole('ROLE_SUPER') or hasRole('Role_LICENSE')")
	@GetMapping("/users")
	public List<User> getAllUsers(Authentication authentication) {

		return userService.findAllUsers();

	}


	@PostMapping("/users")
	public ResponseEntity<User> saveusers(@RequestBody User newUser, Authentication auth) {
		System.out.println(newUser.getUserName() + "  " + auth.getName());
		System.out.println(newUser.getPassword());
		String encodedPassword = passwordEncoder.encode(newUser.getPassword());
		System.out.println(encodedPassword);
		newUser.setPassword(encodedPassword);
		return ResponseEntity.status(HttpStatus.CREATED).body((userService.saveUser(newUser)));

	}
	@PostMapping("/validate")
	public ResponseEntity<Object> validateUser(@RequestBody UserRequest user) {
		String token=userService.getUserToken(user.getUsername(),user.getPassword());


		return ResponseEntity.ok(Collections.singletonMap("Token", token));

	}

	//@PreAuthorize("@userSecurity.hasUserId(authentication,#userId)")
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId, Authentication authentication) {
		System.out.println("Inside getuserbyid method");
		return ResponseEntity.ok().body(userService.findUserById(userId).get());

	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") int UserId, @RequestBody User newUser) {
		return ResponseEntity.ok().body(userService.updateUser(UserId, newUser));

	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") int UserId) {
		userService.deleteUser(UserId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@GetMapping("/users/search")
	@PostAuthorize("returnObject.body.userName==authenticated.user")
	public ResponseEntity<User> userDetails(Authentication authentication, @RequestParam("cname") String cName) throws Exception {
		System.out.println(authentication.getName().toString());
		User User = userService.findByUserName(cName);
		if (User == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.ok().body(User);

	}



}
