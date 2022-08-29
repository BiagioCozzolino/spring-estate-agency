package team1.model;

public class Apartment extends Estate {

	private Integer NumBalconies;
	private Boolean hasCarSpot;
	
	public Integer getNumBalconies() {
		return NumBalconies;
	}
	public void setNumBalconies(Integer numBalconies) {
		NumBalconies = numBalconies;
	}
	public Boolean getHasCarSpot() {
		return hasCarSpot;
	}
	public void setHasCarSpot(Boolean hasCarSpot) {
		this.hasCarSpot = hasCarSpot;
	}
	
}
