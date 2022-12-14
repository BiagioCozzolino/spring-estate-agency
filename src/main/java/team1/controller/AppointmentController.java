package team1.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team1.model.Agent;
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

	@GetMapping("appointmentListAdmin")
	public String appointmentListAdmin(Model model) {
		List<Appointment> appointmentListAdmin = (List<Appointment>) appRepo.findAllByOrderByDateAscHourAsc();
		model.addAttribute("appointmentListAdmin", appointmentListAdmin);
		return "/admin/adminAppointmentList";
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

	@PostMapping("/edit")
	public String save(Model model, @Valid @ModelAttribute("appointment") Appointment formAppointment,
			BindingResult br, RedirectAttributes ra) {

		Agent agent = formAppointment.getAgent();
		List<Appointment> appList = agent.getAppointment();

		boolean validDate = true;
		boolean hasErrors = br.hasErrors();

		
		for(Appointment a : appList)
		{
			if((a.getDate().equals(formAppointment.getDate()) && (a.getHour()== formAppointment.getHour())))
			{
				validDate=false;
			}
		}

		if (!validDate) {
			br.addError(new FieldError("appointment", "hour", "L'orario selezionato per tale data ?? gi?? occupato."));
			hasErrors = true;
		}
		if (hasErrors)

		{
			model.addAttribute("estate", formAppointment.getEstate());
			model.addAttribute("appointment", formAppointment);
			return "appointment/edit";
		} else {
			formAppointment.setStatus("Da effettuare");
			ra.addFlashAttribute(
					"successMessage", 
					"Appuntamento prenotato in data " + 
					formAppointment.getFormattedDate() + 
					" alle ore " +
					formAppointment.getHour()+
					":00");
			appRepo.save(formAppointment);
			return "redirect:/estate/"+ formAppointment.getEstate().getId();
			
		}
	}

	@PostMapping("/appointmentListAdmin/{id}")
	public String appointmentPartUpdate(@PathVariable("id") Integer appointmentId,
			@RequestParam(name = "status") String status) {
		Optional<Appointment> result = appRepo.findById(appointmentId);

		if (result.isPresent()) {
			result.get().setStatus(status);
		}
		try {
			appRepo.save(result.get());
		} catch (Exception e) {
			return "/appointmentListAdmin";
		}
		return "redirect:/appointment/appointmentListAdmin";
	}
}
