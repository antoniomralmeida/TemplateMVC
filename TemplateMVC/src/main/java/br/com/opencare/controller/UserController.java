package br.com.opencare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.model.User;
import br.com.opencare.service.UserService;

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

	@RequestMapping(value = "postregister", method = RequestMethod.POST)
	public ModelAndView sendUserForm(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("registerForm");
			return model;
		}
		userService.save(user);
		request.getSession().setAttribute("user", user.getName());
		return new ModelAndView("wellcome");
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}

}