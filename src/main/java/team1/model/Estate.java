package team1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Estate {

	//id dell'immobile incrementale
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	//indirizzo che non può essere vuoto o nullo
	@NotEmpty(message = "Non esistono immobili senza indirizzo")
	@Column(nullable = false)
	private String address;
	
	//prezzo non nullo o negativo
	@NotNull
	@DecimalMin("0.00")
	private Double price;
	
	@NotEmpty(message = "Tutti gli immobili hanno una classe energetica")
	@Column(nullable = false)
	private String energyClass;
	
	@NotEmpty(message = "Gli immobili devono essere di un tipo")
	@Column(nullable = false)
	private String type;
	
	@NotNull
	@Min(1)
	private Integer NumSpaces;
	
	@NotNull
	@Min(1)
	private Integer NumBathrooms;
	
	//relazione one to many con l'agente immobiliare
	@ManyToOne
	private Agent agent;
	
	@NotNull
	@Min(0)
	private Integer NumViews;
	
	@NotNull
	@Min(0)
	private Integer NumLikes;

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
		return NumSpaces;
	}

	public void setNumSpaces(Integer numSpaces) {
		NumSpaces = numSpaces;
	}

	public Integer getNumBathrooms() {
		return NumBathrooms;
	}

	public void setNumBathrooms(Integer numBathrooms) {
		NumBathrooms = numBathrooms;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Integer getNumViews() {
		return NumViews;
	}

	public void setNumViews(Integer numViews) {
		NumViews = numViews;
	}

	public Integer getNumLikes() {
		return NumLikes;
	}

	public void setNumLikes(Integer numLikes) {
		NumLikes = numLikes;
	}
	
	
}
