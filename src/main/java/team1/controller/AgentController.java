package team1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/edit")
	public String agentForm(Model model) {
		model.addAttribute("agent", new Agent());
		return "admin/agentEdit";
	}

	@PostMapping("/edit")
	public String save(@Valid @ModelAttribute("agent") Agent formAgent, BindingResult br) {
		boolean hasErrors = br.hasErrors();
		boolean validName = true;
		if (formAgent.getId() != null) {
			Agent agentOld = agentRepo.findById(formAgent.getId()).get();
			if (agentOld.getName().equalsIgnoreCase(formAgent.getName()))
				validName = false;
		}
		if (validName && agentRepo.countByName(formAgent.getName()) > 0) {

			br.addError(new FieldError("agent", "name", "Hai già un agente con questo nome"));
			hasErrors = true;

		}
		if (hasErrors)
			return "/admin/agentEdit";

		else {

			agentRepo.save(formAgent);
			return "redirect:/agent";

		}
	}
}
