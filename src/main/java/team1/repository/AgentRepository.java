package team1.repository;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer> {

}
