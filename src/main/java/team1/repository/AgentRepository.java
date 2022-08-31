package team1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team1.model.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer> {

}
