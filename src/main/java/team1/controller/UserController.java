package team1.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team1.repository.UserRepository;

@Controller
@RequestMapping("/user")
class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping
	public String catalog(Model model) {
		List<User> userList = (List<User>) userRepo.findAll();
		model.addAttribute("userList", userList);
		return "admin/userList";
	}
}
