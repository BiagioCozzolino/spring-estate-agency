package team1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team1.model.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer> {

	public List<Agent> findAllByOrderBySurname();

	public Integer countByName(String name);

	public Optional<Agent> findByEmail(String email);

}
