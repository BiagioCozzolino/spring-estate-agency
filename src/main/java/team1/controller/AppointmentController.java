package team1.controller;

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

import team1.model.Appointment;
import team1.model.Estate;
import team1.repository.AppointmentRepository;
import team1.repository.EstateRepository;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appRepo;

	@Autowired
	private EstateRepository estateRepo;

	@GetMapping("success")
	public String success(Model model) {
		return "/appointment/successPage";
	}

	@GetMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer estateId, Model model) {
		Optional<Estate> result = estateRepo.findById(estateId);
		if (result.isPresent()) {
			model.addAttribute("estate", result.get());
			model.addAttribute("appointment", new Appointment());
			return "appointment/edit";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'Immobile con id " + estateId + "non esiste");
		}

	}

	@PostMapping("/edit/{id}")
	public String save(@PathVariable("id") Integer estateId, Model model,
			@Valid @ModelAttribute("appointment") Appointment formAppointment, BindingResult br) {
		Optional<Estate> result = estateRepo.findById(estateId);
		boolean hasErrors = br.hasErrors();
		boolean validDate = true;
		if (formAppointment.getId() != null) {
			Appointment appointmentOld = appRepo.findById(formAppointment.getId()).get();
			if (appointmentOld.getDate().equals(formAppointment.getDate()))
				validDate = false;
		}
		if (validDate) {

			br.addError(new FieldError("appointment", "name", "L'orario o data selezionato è già presente"));
			hasErrors = true;

		}
		if (hasErrors) {
			return "redirect:/appointment/edit/" + result.get().getId();
		} else {

			appRepo.save(formAppointment);
			return "redirect:/appointment/success";

		}
	}
}
