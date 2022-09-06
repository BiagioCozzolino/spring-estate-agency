package team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team1.model.Appointment;
import team1.repository.AppointmentRepository;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appRepo;

	@GetMapping
	public String appointment(Model model) {
		List<Appointment> appointmentList = (List<Appointment>) appRepo.findAll();
		model.addAttribute("appointmentList", appointmentList);
		return "/appointment/edit";
	}
}
