package br.com.opencare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WellcomeController {
	@RequestMapping("/wellcome")
	public String wellcome(ModelMap model) {
		return "wellcome";
	}

}