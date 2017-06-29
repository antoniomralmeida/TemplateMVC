package br.com.opencare.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.model.User;
import br.com.opencare.services.UserService;

@Controller
@RequestMapping("/user/*")

public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listUserForm(Model model) {
		model.addAttribute("users", userService.findAll());
		return "listUserForm";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView sendUserForm() {
		ModelAndView model = new ModelAndView("registerForm");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String sendUserForm(@ModelAttribute("user") User user, Model model) {
		if (user != null) {
			// userService..add(user);
			model.addAttribute("user", user);
			model.addAttribute("msg", "Usuário inderido com sucesso!");
		}
		return "userDetais";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		ModelAndView model = new ModelAndView("loginForm");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "postlogin", method = RequestMethod.POST)
	public String makeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user,
			Model model) {
		request.getSession().setAttribute("user", user.getEmail());
		return "redirect:/";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}

}