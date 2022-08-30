package team1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team1.model.Estate;

public interface EstateRepository extends CrudRepository<Estate, Integer> {
	
	public List<Estate> findByZipCode(String zipCode);
	
	public List<Estate> findByCityIgnoreCase(String city);
	
	
}
