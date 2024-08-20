package com.yonyk.PlanWithJ.jwt;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yonyk.PlanWithJ.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails{
	
	private final User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		this.user.getUser_roles().forEach(R -> {
			authorities.add(() -> R.getRole().getRole_name());
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPwd();
	}

	@Override
	public String getUsername() {
		return this.user.getUser_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
