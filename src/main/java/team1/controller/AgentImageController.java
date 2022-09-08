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

import team1.model.AgentImage;
import team1.model.AgentImageForm;
import team1.repository.AgentImageRepository;
import team1.service.AgentImageService;

@Controller
@RequestMapping("/image")
public class AgentImageController {

	@Autowired
	private AgentImageService service;
	@Autowired
	private AgentImageRepository imageRepo;

	@GetMapping("/list/{agentId}")
	public String agentImages(@PathVariable("agentId") Integer agentId, Model model) {
		List<AgentImage> image = service.getImageByAgentId(agentId);
		AgentImageForm imageForm = service.createImageForm(agentId);
		model.addAttribute("imageList", image);
		model.addAttribute("imageForm", imageForm);
		return "/agentImage/list";
	}

	@PostMapping("/save")
	public String saveImage(@ModelAttribute("ImageForm") AgentImageForm imageForm) {
		try {
			AgentImage savedImage = service.createImage(imageForm);
			return "redirect:/image/list/" + savedImage.getAgent().getId();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}

	@RequestMapping(value = "/{agentId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContent(@PathVariable("agentId") Integer agentId) {
		byte[] content = service.getImageContent(agentId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer imageId, RedirectAttributes ra, Model model) {
		Optional<AgentImage> result = imageRepo.findById(imageId);
		if (result.isPresent()) {
			imageRepo.delete(result.get());
			ra.addFlashAttribute("successMessage", "L'immagine è stata cancellata con successo!");
			return "redirect:/image/list/" + result.get().getAgent().getId();

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'immagine con id" + imageId + " non esiste");
		}

	}
}
