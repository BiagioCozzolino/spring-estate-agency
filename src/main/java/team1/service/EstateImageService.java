package team1.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.model.Estate;
import team1.model.EstateImage;
import team1.model.EstateImageForm;
import team1.repository.EstateImageRepository;
import team1.repository.EstateRepository;

@Service
public class EstateImageService {
	
	@Autowired
	private EstateImageRepository imageRepo;
	
	@Autowired
	private EstateRepository estateRepo;
	
	public List<EstateImage> getImageByEstateId(Integer estateId)
	{
		Estate estate = estateRepo.findById(estateId).get();
		return imageRepo.findByEstate(estate);
	}
	
	public EstateImageForm createImageForm(Integer estateId) {
		Estate estate = estateRepo.findById(estateId).get();
		EstateImageForm img = new EstateImageForm();
		img.setEstate(estate);
		return img;
	}

	public EstateImage createImage(EstateImageForm imageForm) throws IOException {
		EstateImage imgToSave = new EstateImage();
		imgToSave.setEstate(imageForm.getEstate());
		if (imageForm.getContentMultipart() != null) {
			byte[] contentSerialized = imageForm.getContentMultipart().getBytes();
			imgToSave.setContent(contentSerialized);
		}
		return imageRepo.save(imgToSave);
	}

	public byte[] getImageContent(Integer id) {
		EstateImage img = imageRepo.findById(id).get();
		return img.getContent();
	}
}
