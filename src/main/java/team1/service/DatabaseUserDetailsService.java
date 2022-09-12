package team1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import team1.model.Agent;
import team1.repository.AgentRepository;
import team1.security.DatabaseUserDetails;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private AgentRepository agentRepo;

	// Metodo per trovare dal database user a partire dallo username in questo caso
	// l'email
	// Se lo trova genera un DatabaseUserDetail copiando i dati di User
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Agent> agent = agentRepo.findByEmail(username);
		// Se trova l'email entra qui e crea il nuovo ogetto
		if (agent.isPresent()) {
			return new DatabaseUserDetails(agent.get());
			// Se non lo trova lancia un eccezione
		} else {
			throw new UsernameNotFoundException(username + "Non esiste un account con questa E-Mail");
		}
	}

}
