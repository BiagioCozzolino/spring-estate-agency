package team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team1.model.Agent;
import team1.repository.AgentRepository;

@Controller
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentRepository agentRepo;

	@GetMapping
	public String agentList(Model model) {
		List<Agent> agentList = (List<Agent>) agentRepo.findAll();
		model.addAttribute("agentList", agentList);
		return "admin/agentList";
	}
}
