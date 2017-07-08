package br.com.opencare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.model.User;
import br.com.opencare.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		ModelAndView model = new ModelAndView("loginForm");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String makeLogin(ModelMap model, @Valid User user, BindingResult result, HttpServletRequest request) {

		if (!userService.login(user.getEmail(), user.getPwd())) {
			model.put("errorMessage", "Invalid Credentials!");
			return "loginForm";
		}
		request.getSession().setAttribute("user", user.getEmail());
		return "redirect:/";
	}

}
