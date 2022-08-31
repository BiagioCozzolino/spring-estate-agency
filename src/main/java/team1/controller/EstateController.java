package team1.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

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
	@GetMapping("/admin/estateList")
	public String estatesForAdmin(Model model) {
		List<Estate> estateListForAdmin = (List<Estate>) estateRepo.findAll();
		model.addAttribute("estateListAdmin", estateListForAdmin);
		return "/admin/estateList";
	}
	
	@GetMapping("/{id}")
	public String estateDetail(@PathVariable ("id") Integer estateId, Model model)
	{
		Optional<Estate> result = estateRepo.findById(estateId);
		if(result.isPresent())
		{
			Estate modelEstate = result.get();
			model.addAttribute("estate", modelEstate);
			return "/estate/detail";
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Immobile non trovato");

		}
	}

	@GetMapping("/admin/estateList/edit")
	public String estateForm(Model model)
	{
		model.addAttribute("estate", new Estate());
		model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
		return "admin/editEstate";
	}
	
}
