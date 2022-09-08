package team1.model;

import org.springframework.web.multipart.MultipartFile;

public class EstateImageForm {

	private Integer id;
	
	private Estate estate;
	
	private MultipartFile contentMultipart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}
	
	
}
