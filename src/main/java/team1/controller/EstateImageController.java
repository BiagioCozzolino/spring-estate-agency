package team1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team1.model.EstateImage;
import team1.model.EstateImageForm;
import team1.repository.EstateImageRepository;
import team1.service.EstateImageService;

@Controller
@RequestMapping("/estate/image")
public class EstateImageController {
	
	@Autowired
	private EstateImageService service;
	
	@Autowired
	private EstateImageRepository imageRepo;
	
	@GetMapping("/list/{estateId}")
	public String agentImages(@PathVariable("agentId") Integer estateId, Model model) {
		List<EstateImage> image = service.getImageByEstateId(estateId);
		EstateImageForm imageForm = service.createImageForm(estateId);
		model.addAttribute("imageList", image);
		model.addAttribute("imageForm", imageForm);
		return "/image/list";
	}	
	
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("ImageForm") EstateImageForm imageForm) {
		try {
			EstateImage savedImage = service.createImage(imageForm);
			return "redirect:/image/list/" + savedImage.getEstate().getId();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}

	@RequestMapping(value = "/{estateId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContent(@PathVariable("agentId") Integer estateId) {
		byte[] content = service.getImageContent(estateId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer imageId, RedirectAttributes ra, Model model) {
		Optional<EstateImage> result = imageRepo.findById(imageId);
		if (result.isPresent()) {
			imageRepo.delete(result.get());
			ra.addFlashAttribute("successMessage", "L'immagine è stata cancellata con successo!");
			return "redirect:/image/list/" + result.get().getEstate().getId();

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'immagine con id" + imageId + " non esiste");
		}

	}
	
	

}
