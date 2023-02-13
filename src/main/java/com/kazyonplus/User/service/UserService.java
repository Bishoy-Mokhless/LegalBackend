package com.kazyonplus.User.service;


import com.kazyonplus.User.model.User;

import com.kazyonplus.User.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kazyonplus.User.repo.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@AllArgsConstructor

public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private final PasswordEncoder passwordEncoder;

/*	*/
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	public Optional<User> findUserById(int id) {
		return userRepo.findById(id);
	}
	
	public User findByUserName(String userName) {
		
		User user=userRepo.findByUserName(userName);
		return user;
		
	}
	
	public User saveUser(User newUser) {
		
		User user=userRepo.save(newUser);
		return user;
		
	}

	public User updateUser(int id, User user) {
		
		Optional<User> retrievedUser=userRepo.findById(id);
		if(retrievedUser==null)
			try {
				throw new Exception("User not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		userRepo.save(user);
		return userRepo.findById(id).get();
		
	}
	
	public User deleteUser(int userId) {
		
		Optional<User> retrievedUser=userRepo.findById(userId);
		if(retrievedUser==null)
			try {
				throw new Exception("User not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		userRepo.deleteById(userId);
		return retrievedUser.get();
		
		
		
	}
	public String getUserToken(String username,String password){
		String token="";
		if (username == null)
		{
			throw new RuntimeException("USERNAME EMPTY");
			//return
		}
		else
		{
			User user	= userRepo.findByUserName(username);
			if (userRepo.existsById(user.getUserId())){
				HttpHeaders headers = new HttpHeaders();
				headers.setBasicAuth(username, password);
				if (passwordEncoder.matches(password,user.getPassword()))
				{
					token = headers.get("Authorization").toString();

					return token;
				}

				else
					throw new RuntimeException("INCORRECT PASSWORD");
					//return "INCORRECT PASSWORD";

			}
			else
				throw new RuntimeException("user not found");
				//return "USER NOT FOUND";
		}

	}



}








//	public void autoLogin(String userName, String password) {
//		
//		UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
//		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
//		
//		authenticationManager.authenticate(token);
//		
//		if(token.isAuthenticated()) {
//			SecurityContextHolder.getContext().setAuthentication(token);
//		}
//				
//	}


//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private UserDetailsService userDetailsService;

	
	
	
