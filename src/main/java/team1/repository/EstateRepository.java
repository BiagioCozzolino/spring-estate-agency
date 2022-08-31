package team1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team1.model.Estate;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Integer> {
	
	//ricerca per CAP
	public List<Estate> findByZipCodeOrderByAddressAsc(String zipCode);
	
	//ricerca per città
	public List<Estate> findByCityIgnoreCaseOrderByAddressAsc(String city);
	
	//ricerca tipo immobile
	public List<Estate> findByTypeIgnoreCase(String type);
	
	//ricerca indirizzo
	public List<Estate> findByAddressContainsIgnoreCase(String string);
	
	//ricerca per il prezzo
	public List<Estate> findByPriceGreaterThanEqualOrderByAddressAsc(Double price);
	
	public List<Estate> findByPriceLessThanEqualOrderByAddressAsc(Double price);
	
	//ricerca per area immobile
	public List<Estate> findByAreaGreaterThanEqualOrderByAddressAsc(Integer area);
	
	public List<Estate> findByAreaLessThanEqualOrderByAddressAsc(Integer Area);
	
	//ricerca per data da fare
	
	
	//ricerca per classe energetica
	public List<Estate> findByEnergyClassIgnoreCase(String energyClass);
	
	//ricerca per numero vani
	public List<Estate> findByNumSpacesOrderByAddressAsc(Integer numSpaces);
	
	//ricerca per numero di bagni
	public List<Estate> findByNumBathroomsOrderByAddressAsc(Integer numBathrooms);

	//ricerca per numero balconi
	public List<Estate> findByNumBalconiesOrderByAddressAsc(Integer numBalconies);

	//ricerca se ha o meno posto auto
	public List<Estate> findByHasCarSpotOrderByAddressAsc(Boolean hasCarSpot);

	//ricerca se ha o meno posto il giardino
	public List<Estate> findByHasGardenOrderByAddressAsc(Boolean hasGarden);
	
	//filtro per indirizzo, numero civico, interno e CAP
	
	public Optional<Estate> findByAddressAndHouseNumberAndInteriorAndZipCode(String address, Integer houseNumber, Integer interior, String ZipCode);

}
