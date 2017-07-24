package br.com.opencare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.opencare.model.User;
import br.com.opencare.service.UserService;

@Controller
public class WellcomeController {

	@Autowired
	UserService userService;

	@RequestMapping("/wellcome")
	public String wellcome(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName(); // get logged in username
		model.addAttribute("login", login);

		User user = userService.findByEmail(login);
		model.addAttribute(user);
		System.out.println(user.getName());
		return "wellcome";
	}

}