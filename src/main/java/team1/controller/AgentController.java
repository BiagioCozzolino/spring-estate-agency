package team1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import team1.model.Agent;
import team1.repository.AgentRepository;

@Controller
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentRepository agentRepo;
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	public String agentList(Model model) {
		List<Agent> agentList = (List<Agent>) agentRepo.findAll();
		model.addAttribute("agentList", agentList);
		return "admin/agentList";
	}

	@GetMapping("/{id}")
	public String agentDetail(@PathVariable("id") Integer agentId, Model model) {
		Optional<Agent> result = agentRepo.findById(agentId);
		if (result.isPresent()) {
			Agent modelAgent = result.get();
			model.addAttribute("agent", modelAgent);
			return "agent/agentProfile";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profilo agente non trovato");

		}
	}

	@GetMapping("/edit")
	public String agentForm(Model model) {
		model.addAttribute("agent", new Agent());
		return "admin/agentEdit";
	}

	@PostMapping("/edit")
	public String save(@Valid @ModelAttribute("agent") Agent formAgent, BindingResult br) {

		boolean hasErrors = br.hasErrors();

		if (hasErrors)
			return "/admin/agentEdit";

		else {
			formAgent.setPassword(encoder.encode(formAgent.getPassword()));
			agentRepo.save(formAgent);
			return "redirect:/agent";

		}
	}

	@GetMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer agentId, Model model) {
		Optional<Agent> result = agentRepo.findById(agentId);
		if (result.isPresent()) {
			model.addAttribute("agent", result.get());
			agentRepo.save(result.get());
			return "admin/agentEdit";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il agente con id " + agentId + "Non esiste");
		}

	}
}
