package br.com.opencare.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private Log logger = LogFactory.getLog(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) throws Exception {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);

		String errorMsg = "Http error code: 500. Internal Server Error in " + req.getRequestURL();
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMsg", errorMsg);
		mav.addObject("exception", ex);
		mav.setViewName("errorPage");
		return mav;
	}

}
