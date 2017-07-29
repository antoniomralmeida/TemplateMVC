package br.com.opencare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.service.UserProfileService;
import br.com.opencare.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		userProfileService.setupUserProfiles();
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("loginForm");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			// request.getSession().removeAttribute("login");
		}
		return "redirect:/login?logout";// You can redirect wherever you want,
										// but generally it's a good practice to
										// show login screen again.
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		model.addObject("msg", "You've been logged successfully.");
		// request.getSession().setAttribute("login", getLogin());
		model.setViewName("wellcome");
		return model;
	}

}
