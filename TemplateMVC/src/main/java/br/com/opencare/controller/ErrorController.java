package br.com.opencare.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	private Log logger = LogFactory.getLog(HomeController.class);

	// for 403 access denied page
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = auth.getName(); // get logged in username

		String per = auth.getAuthorities().toString();

		ModelAndView model = new ModelAndView();
		String msg = "Hi " + user + ", you do not have permission to access this page!" + per;

		logger.error(msg);

		model.addObject("errorMsg", msg);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/error")
	public ModelAndView doGet(HttpServletRequest req, Exception ex) {
		
		int httpError =0;
		String httpMsg = "Unknown error";
		String uri = "Unknown uri";

		if (req != null) {
			logger.error("Request: " + req.getRequestURL() + " raised " + ex==null?"Unknown exception":ex);
			httpError = (int) req.getAttribute("javax.servlet.error.status_code");
			uri = (String)req.getAttribute("javax.servlet.error.request_uri");
		}
		switch (httpError) {
		case 400:
			httpMsg = "This response means that server could not understand the request due to invalid syntax.";
			break;
		case 401:
			httpMsg = "Authentication is needed to get requested response.";
			break;
		case 404:
			httpMsg = "Server can not find requested resource.";
			break;
		case 408:
			httpMsg = "This response is sent on an idle connection by some servers, even without any previous request by the client.";
			break;
		case 409:
			httpMsg = "This response would be sent when a request conflict with current state of server.";
			break;
		}

		String errorMsg = "Http error code: " + httpError + ". " + httpMsg + " in "
				+ uri;
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMsg", errorMsg);
		mav.addObject("exception", ex);
		mav.setViewName("errorPage");
		return mav;
	}

}
