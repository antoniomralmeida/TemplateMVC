package br.com.opencare.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {

		if (model != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			model.addObject("currentUser", auth);
		}
	}

	/*
	 * @Override public boolean preHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object controller) throws Exception {
	 * 
	 * Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); String user =
	 * auth.getName(); // get logged in username
	 * 
	 * if (!user.equals("anonymousUser")) { return true; }
	 * 
	 * String uri = request.getRequestURI(); System.out.println(uri); if
	 * (uri.endsWith("/home") || uri.endsWith("/splash") ||
	 * uri.endsWith("/error") || uri.endsWith("/login") ||
	 * uri.endsWith("/user/register") || uri.contains("/webjars/") ||
	 * uri.contains("/resources/") || uri.contains("/rest")) { return true; }
	 * 
	 * response.sendRedirect(request.getContextPath() + "/home"); return false;
	 * }
	 */
}