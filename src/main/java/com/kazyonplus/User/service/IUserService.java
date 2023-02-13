
package com.kazyonplus.User.service;


import java.util.List;
import java.util.Optional;
import com.kazyonplus.User.model.User;

public interface IUserService {

	
	public List<User> findAllUsers() ;

	public Optional<User> findUserById(int id);
	
	public User findByUserName(String userName) ;
	public String getUserToken(String username,String password);


}
