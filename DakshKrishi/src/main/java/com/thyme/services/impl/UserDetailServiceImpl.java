//package com.thyme.services.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.thyme.entities.User;
//import com.thyme.exceptions.ResourceNotFoundException;
//import com.thyme.repositories.UserRepo;
//
//public class UserDetailServiceImpl implements UserDetailsService {
//
//	
//	@Autowired
//	private UserRepo userRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		
//		User user = userRepo.getUserByName(username);
//		
//		if(user == null) {
//			throw new ResourceNotFoundException("User", "id", userName);
//		}
//		
//		
//		
//		
//		return null;
//	}
//
//}
