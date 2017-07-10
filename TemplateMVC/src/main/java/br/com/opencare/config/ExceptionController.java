package br.com.opencare.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
@Controller
public class ExceptionController {

	private Log logger = LogFactory.getLog(ExceptionController.class);

	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public ModelAndView doPost(HttpServletRequest req, Exception exception) {
		return handleError(req, exception);
	}

	@ExceptionHandler(value = Exception.class)
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);

		String errorMsg = "";
		int httpErrorCode = (Integer) req.getAttribute("javax.servlet.error.status_code");

		errorMsg = "CODE:" + httpErrorCode;

		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}

		}

		ModelAndView mav = new ModelAndView("errorPage");
		mav.addObject("errorMsg", errorMsg);
		mav.addObject("url", req.getContextPath());
		mav.addObject("exception", exception);
		return mav;
	}
}