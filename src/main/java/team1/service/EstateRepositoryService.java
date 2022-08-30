package team1.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import team1.model.Estate;
import team1.repository.EstateRepository;

public class EstateRepositoryService implements EstateRepository {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Estate estate;
	
	private LocalDate today = LocalDate.now();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//metodi da non utilizzare
	@Override
	public <S extends Estate> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Estate> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Estate> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Estate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Estate> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Estate entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Estate> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estate> findByZipCodeOrderByAddressAsc(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByCityIgnoreCaseOrderByAddressAsc(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByTypeIgnoreCase(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByAddressContainsIgnoreCase(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByPriceGreaterThanEqualOrderByAddressAsc(Double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByPriceLessThanEqualOrderByAddressAsc(Double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByAreaGreaterThanEqualOrderByAddressAsc(Integer area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByAreaLessThanEqualOrderByAddressAsc(Integer Area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByEnergyClassIgnoreCase(String energyClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByNumSpacesOrderByAddressAsc(Integer numSpaces) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByNumBathroomsOrderByAddressAsc(Integer numBathrooms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByNumBalconiesOrderByAddressAsc(Integer numBalconies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByHasCarSpotOrderByAddressAsc(Boolean hasCarSpot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estate> findByHasGardenOrderByAddressAsc(Boolean hasGarden) {
		// TODO Auto-generated method stub
		return null;
	}

}
