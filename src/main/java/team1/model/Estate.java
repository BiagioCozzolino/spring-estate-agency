package team1.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Estate {

	// class properties

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	// id dell'immobile incrementale

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Non esistono immobili senza indirizzo")
	@Column(nullable = false)
	private String address;
	
	private Integer houseNumber;
	
	@NotNull(message= "Tutti gli immobili sono posizionati su un piano")
	@Column(nullable = false)
	private Integer floorNumber;
	
	private Integer interior;

	@NotEmpty(message = "Non esistono immobili senza CAP")
	@Column(nullable = false)
	private String zipCode;

	@NotEmpty(message = "Non esistono immobili senza CAP")
	@Column(nullable = false)
	private String city;

	@NotNull
	@Min(1)
	private Integer area;

	@NotNull
	@DecimalMin("0.00")
	private Double price;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate insertionDate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate contractStart;

	@NotEmpty(message = "Tutti gli immobili devono avere uno status")
	@Column(nullable = false)
	private String status;

	@NotEmpty(message = "Tutti gli immobili hanno una classe energetica")
	@Column(nullable = false)
	private String energyClass;

	@NotEmpty(message = "Gli immobili devono essere di un tipo")
	@Column(nullable = false)
	private String type;

	@NotNull
	@Min(1)
	private Integer numSpaces;

	@NotNull
	@Min(0)
	private Integer numBathrooms;

	@Min(0)
	private Integer numBalconies;


	private Boolean hasCarSpot;


	private Boolean hasGarden;


	@ManyToOne
	@NotNull
	private Agent agent;
	
	@OneToMany(mappedBy = "estate")
	private List<EstateImage> images;

	
	private Integer numViews=0;

	//@ColumnDefault("0")
	private Integer numLikes=0;

	// Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getEnergyClass() {
		return energyClass;
	}

	public void setEnergyClass(String energyClass) {
		this.energyClass = energyClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumSpaces() {
		return numSpaces;
	}

	public void setNumSpaces(Integer numSpaces) {
		this.numSpaces = numSpaces;
	}

	public Integer getNumBathrooms() {
		return numBathrooms;
	}

	public void setNumBathrooms(Integer numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Integer getNumViews() {
		return numViews;
	}

	public void setNumViews(Integer numViews) {
		this.numViews = numViews;
	}

	public Integer getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(Integer numLikes) {
		this.numLikes = numLikes;
	}

	public Integer getNumBalconies() {
		return numBalconies;
	}

	public void setNumBalconies(Integer numBalconies) {
		this.numBalconies = numBalconies;
	}

	public Boolean getHasCarSpot() {
		return hasCarSpot;
	}

	public void setHasCarSpot(Boolean hasCarSpot) {
		this.hasCarSpot = hasCarSpot;
	}

	public Boolean getHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(Boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(LocalDate insertionDate) {
		this.insertionDate = insertionDate;
	}

	public String getFormattedInsertionDate() {
		return insertionDate.format(dateFormatter);
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getContractStart() {
		return contractStart;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getInterior() {
		return interior;
	}

	public void setInterior(Integer interior) {
		this.interior = interior;
	}

	public void setContractStart(LocalDate contractStart) {
		this.contractStart = contractStart;
	}

	public List<EstateImage> getImages() {
		return images;
	}

	public void setImages(List<EstateImage> images) {
		this.images = images;
	}

	public String getFormattedContractStart() {
		return contractStart.format(dateFormatter);
	}

	public Integer getStatusValue(String status) {
		Integer res = 0;

		switch (status) {
		case "In vendita":
			res = 4;
			break;
		case "In affitto":
			res = 3;
			break;
		case "Venduto":
			res = 2;
			break;
		case "Affittato":
			res = 1;
			break;
		case "Annullato":
			res = 0;
			break;
		}

		return res;

	}
}
