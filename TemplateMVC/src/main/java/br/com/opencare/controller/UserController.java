package br.com.opencare.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.model.State;
import br.com.opencare.model.User;
import br.com.opencare.model.UserProfile;
import br.com.opencare.service.UserProfileService;
import br.com.opencare.service.UserService;

@Controller
@RequestMapping("/user/*")
@SessionAttributes("roles,states")

public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserProfileService userProfileService;

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

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView sendUserForm(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("registerForm");
			return model;
		} else {
			user.setPwd(passwordEncoder.encode(user.getPwd()));
			userService.save(user);
			ModelAndView model = new ModelAndView("loginForm");
			model.addObject("username", user.getEmail());
			return model;
		}
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView sendUserEditForm(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("editUserForm");
		model.addObject("user", userService.findOne(id));
		return model;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView sendUserEditForm(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("editUserForm");
			return model;
		} else {
			System.out.println(user);
			userService.save(user);
			ModelAndView model = new ModelAndView("editUserForm");
			return model;
		}
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return (List<UserProfile>) userProfileService.findAll();
	}

	@ModelAttribute("states")
	public List<String> initializeStates() {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < State.values().length; i++)
			s.add(State.values()[i].getState());
		return s;
	}

}