package br.com.opencare.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.opencare.dao.PAGINING;
import br.com.opencare.model.Criteria;
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
	UserProfileService userProfileService;

	@Autowired
	private MessageSource messageSource;

	private Log logger = LogFactory.getLog(ErrorController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView listUserForm() {
		ModelAndView model = new ModelAndView("userList");
		model.addObject("criteria", new Criteria());
		return model;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ModelAndView findUserForm(@Valid @ModelAttribute("criteria") Criteria criteria) {
		ModelAndView model = new ModelAndView("userList");
		int page = 1;
		long size = userService.countByCriteria(criteria.getCriteria());
		model.addObject("userList", userService.findByCriteria(criteria.getCriteria(), page));
		model.addObject("pages", PAGINING.pages(size));
		model.addObject("page", page);
		model.addObject("criteria", criteria);
		return model;
	}

	@RequestMapping(value = "list/{page}", method = RequestMethod.GET)
	public ModelAndView findUserForm(@PathVariable("page") int page, @RequestParam("criteria") String criteria) {

		ModelAndView model = new ModelAndView("userList");

		long size = userService.countByCriteria(criteria);

		model.addObject("userList", userService.findByCriteria(criteria, page));
		model.addObject("pages", PAGINING.pages(size));
		model.addObject("page", page);
		model.addObject("criteria", new Criteria(criteria));

		return model;
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView sendUserForm() {
		userProfileService.setupUserProfiles();
		ModelAndView model = new ModelAndView("userRegister");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView sendUserForm(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest req) {
		if (result.hasErrors()) {
			logger.error("Request: " + req.getRequestURL() + " raised " + result.getAllErrors().toString());
			ModelAndView model = new ModelAndView("userRegister");
			return model;
		} else {
			user.setPwd(passwordEncoder.encode(user.getPwd()));

			/*for (int i = 100; i < 120; i++) {
				User u = new User();
				u.setName(user.getName() + i);
				u.setEmail(user.getEmail() + i);
				u.setPwd(user.getPwd());
				System.out.println(u);
				userService.save(u);
			}
*/
			userService.save(user);
			ModelAndView model = new ModelAndView("loginForm");
			model.addObject("username", user.getEmail());
			return model;
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView sendUserEditForm(@RequestParam("login") String login) {
		ModelAndView model = new ModelAndView("userEdit");
		model.addObject("user", userService.findByEmail(login));
		return model;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView sendUserEditForm(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest req, Locale loc) {
		if (result.hasErrors()) {
			logger.error("Request: " + req.getRequestURL() + " raised " + result.getAllErrors().toString());

			ModelAndView model = new ModelAndView("userEdit");
			return model;
		} else {
			User u = userService.findByEmail(user.getEmail());
			user.setPwd(u.getPwd()); // o campo pwd foi omitido do formulário
										// por questão de segurança
			userService.save(user);
			ModelAndView model = new ModelAndView("home");
			model.addObject("message", messageSource.getMessage("done.message", null, loc));
			return model;
		}
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String sendUserEditForm(@PathVariable("id") long id, ModelMap model) {
		if (userService.find(id) == null)
			model.addAttribute("msg", "Not Found[" + id + "]!");
		else {
			userService.delete(id);
			model.addAttribute("msg", "Deleted ID[" + id + "]!");
		}
		return "empty";
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