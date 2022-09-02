package team1.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team1.model.Estate;
import team1.model.EstateImageForm;
import team1.repository.AgentRepository;
import team1.repository.EstateRepository;
import team1.service.EstateImageService;

@Controller
@RequestMapping("/estate")
public class EstateController {

	@Autowired
	private EstateRepository estateRepo;

	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private EstateImageService service;

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
	public String estateAddForm(Model model)
	{
		model.addAttribute("estate", new Estate());
		model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
		model.addAttribute("imageForm", new EstateImageForm());
		return "admin/estateEdit";
	}
	
	@GetMapping("/admin/estateList/edit/{id}")
	public String estateEdit(@PathVariable ("id") Integer estateId, Model model)
	{
		Optional<Estate> result = estateRepo.findById(estateId);
		
		if(result.isPresent())
		{
			model.addAttribute("estate", result.get());
			model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
			model.addAttribute("imageForm", service.createImageForm(estateId));
			return "admin/estateEdit";
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Questo immobile non è presente");

		}
	}
	
	@PostMapping("/admin/estateList/edit")
	public String estateSave(@Valid @ModelAttribute("estate") Estate formEstate, BindingResult br, Model model )
	{
		boolean hasErrors = br.hasErrors();
		boolean validateEstate = true;
		
		if(formEstate.getId()!=null) //controllo se sto modificando o meno un immobile
		{
			Estate notUpdatedEstate = estateRepo.findById(formEstate.getId()).get();
			if		(
						notUpdatedEstate.getAddress().equalsIgnoreCase(formEstate.getAddress()) &&
						notUpdatedEstate.getHouseNumber()==formEstate.getHouseNumber() &&
						notUpdatedEstate.getInterior()==formEstate.getInterior() &&
						notUpdatedEstate.getZipCode().equalsIgnoreCase(formEstate.getZipCode())
					)
				validateEstate=false;
		}
		
		if(formEstate.getId()==null)
		{
			Optional<Estate> result = estateRepo.findByAddressAndHouseNumberAndInteriorAndZipCode(formEstate.getAddress(), formEstate.getHouseNumber(), formEstate.getInterior(), formEstate.getZipCode());
			if(!result.isPresent()) //se non è presente alcun immobile con gli stessi dati
				validateEstate=false; //non richiedo la validazione dell'immobile
		}
		
		if(validateEstate)
		{
			br.addError(new FieldError("estate", "address", "Immobile già presente nel database, non è possibile crearlo di nuovo, solo modificarlo"));
			model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
			return "admin/estateEdit";
		}
		
		if(hasErrors)
		{
			model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
			return "admin/estateEdit";
		}
		else
		{
			try
			{
				estateRepo.save(formEstate);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", "Non è stato possibile salvare i dati inseriti");
				model.addAttribute("agentList", agentRepo.findAllByOrderBySurname());
				return "admin/estateEdit";
			}
			return "redirect:/estate/admin/estateList";
		}
	}

	@GetMapping("/admin/estate/delete/{id}")
	public String deleteEstate(@PathVariable("id") Integer estateId, RedirectAttributes ra)
	{
		Optional<Estate> result = estateRepo.findById(estateId);
		if(result.isPresent())
		{
			result.get().setStatus("Annullato");
			estateRepo.save(result.get());
			ra.addFlashAttribute("successMessage", "L'immobile in " + result.get().getAddress()+ ", "+ result.get().getHouseNumber()+ " " + result.get().getZipCode()+ " è stato inserito nella lista annullati.");
			return "redirect: /admin/estateList";
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
			          "L'immobile " + estateId + " non trovato");
		}
	}

	

}
