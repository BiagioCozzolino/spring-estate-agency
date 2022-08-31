package team1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team1.model.Agent;
import team1.model.AgentImage;

@Repository
public interface AgentImageRepository extends CrudRepository<AgentImage, Integer> {

	public List<AgentImage> findByAgent(Agent agent);
}
