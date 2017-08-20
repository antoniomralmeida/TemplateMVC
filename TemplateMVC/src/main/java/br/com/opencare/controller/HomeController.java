package br.com.opencare.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.opencare.model.User;
import br.com.opencare.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/home")
	public String splash(ModelMap model) {

		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("getWelcome is executed!");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = auth.getName(); // get logged in username
		if (user.equals("anonymousUser"))
			return "splash";
		else
			return "home";
	}

	@RequestMapping("/wellcome")
	public String wellcome(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName(); // get logged in username
		model.addAttribute("login", login);

		User user = userService.findByEmail(login);
		model.addAttribute(user);
		return "wellcome";
	}
}