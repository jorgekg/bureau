package br.com.bureau.gateway.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.bureau.gateway.models.enums.Role;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String password, List<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getDresciption()))
				.collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
