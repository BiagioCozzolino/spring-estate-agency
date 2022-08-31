package team1.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team1.model.Agent;
import team1.model.Estate;
import team1.repository.AgentRepository;
import team1.repository.EstateRepository;

@Controller
@RequestMapping("/estate")
public class EstateController {

	@Autowired
	private EstateRepository estateRepo;

	@Autowired
	private AgentRepository agentRepo;

	private List<Estate> estateListForUsers;

	// pagina con la lista di tutti gli immobili per gli utenti
	@GetMapping
	public String estates(Model model) {
		Period diff;
		Integer daysDiff = 0;
		estateListForUsers = null;
		List<Estate> estateList = (List<Estate>) estateRepo.findAll();

		for (Estate e : estateList) {

			if (e.getStatusValue(e.getStatus()) == 4 || e.getStatusValue(e.getStatus()) == 3) {
				estateListForUsers.add(e);
			}

			if (e.getContractStart() != null) {
				diff = LocalDate.now().until(e.getContractStart());
				daysDiff = diff.getDays();
			}

			if (daysDiff <= 7) {
				estateListForUsers.add(e);
			}
		}

		model.addAttribute("estateList", estateListForUsers);
		return "/home/estateList";
	}

	// pagina con la lista di tutti gli immobili per l'admin
	@GetMapping("/admin")

	public String estatesForAdmin(Model model) {
		List<Estate> estateListForAdmin = (List<Estate>) estateRepo.findAll();
		List<Agent> agentListForAdmin = (List<Agent>) agentRepo.findAll();
		model.addAttribute("estateListAdmin", estateListForAdmin);
		model.addAttribute("agentListAdmin", agentListForAdmin);
		return "/admin/estateList";
	}
}
