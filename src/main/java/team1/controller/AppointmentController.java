package team1.controller;

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

import team1.model.Appointment;
import team1.repository.AppointmentRepository;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appRepo;

	@GetMapping
	public String agentForm(Model model) {
		model.addAttribute("appointment", new Appointment());
		return "appointment/edit";
	}

	@GetMapping("success")
	public String success(Model model) {
		return "/appointment/successPage";
	}

	@PostMapping("/edit")
	public String save(@Valid @ModelAttribute("appointment") Appointment formAppointment, BindingResult br) {
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
		if (hasErrors)
			return "/appointment/edit";

		else {

			appRepo.save(formAppointment);
			return "redirect:/appointment/success";

		}
	}
}
