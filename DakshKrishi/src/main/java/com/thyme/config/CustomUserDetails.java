package com.thyme.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thyme.entities.User;

public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	
	
	@Override
	public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
	}
	
	@Override
	public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getName();
	}

}
