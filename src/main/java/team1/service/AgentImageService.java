package team1.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.model.Agent;
import team1.model.AgentImage;
import team1.model.AgentImageForm;
import team1.repository.AgentImageRepository;
import team1.repository.AgentRepository;

@Service
public class AgentImageService {

	@Autowired
	private AgentImageRepository imageRepo;

	@Autowired
	private AgentRepository agentRepo;

	public List<AgentImage> getImageByAgentId(Integer agentId) {

		Agent agent = agentRepo.findById(agentId).get();
		return imageRepo.findByAgent(agent);

	}

	public AgentImageForm createImageForm(Integer agentId) {
		Agent agent = agentRepo.findById(agentId).get();
		AgentImageForm img = new AgentImageForm();
		img.setAgent(agent);
		return img;
	}

	public AgentImage createImage(AgentImageForm imageForm) throws IOException {
		AgentImage imgToSave = new AgentImage();
		imgToSave.setAgent(imageForm.getAgent());
		if (imageForm.getContentMultipart() != null) {
			byte[] contentSerialized = imageForm.getContentMultipart().getBytes();
			imgToSave.setContent(contentSerialized);
		}
		return imageRepo.save(imgToSave);
	}

	public byte[] getImageContent(Integer id) {
		AgentImage img = imageRepo.findById(id).get();
		return img.getContent();
	}
}
