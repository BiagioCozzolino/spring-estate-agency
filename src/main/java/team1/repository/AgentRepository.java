package team1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team1.model.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer> {

	public List<Agent> findAllByOrderBySurname();

}
