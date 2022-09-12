package team1.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import team1.model.Agent;
import team1.model.Role;

public class DatabaseUserDetails implements UserDetails {

	// Attributi
	private String email;
	private String password;
	private Set<GrantedAuthority> authoritires;

	// Costruttore per generare un databseUserDetails a partite da un ogetto agente
	public DatabaseUserDetails(Agent agent) {
		this.email = agent.getEmail();
		this.password = agent.getPassword();
		this.authoritires = new HashSet<GrantedAuthority>();
		for (Role r : agent.getRoles()) {
			this.authoritires.add(new SimpleGrantedAuthority(r.getName()));
		}
	};

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authoritires;
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
