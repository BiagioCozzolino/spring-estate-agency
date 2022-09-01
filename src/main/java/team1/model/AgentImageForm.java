package team1.model;

import org.springframework.web.multipart.MultipartFile;

public class AgentImageForm {

	private Integer id;

	private Agent agent;

	private MultipartFile contentMultipart;

	// Getter and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}

}
