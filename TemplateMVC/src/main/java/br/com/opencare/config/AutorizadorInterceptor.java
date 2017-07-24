package br.com.opencare.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = auth.getName(); // get logged in username

		request.getSession().setAttribute("user", user);

		if (!user.equals("anonymousUser")) {
			return true;
		}

		String uri = request.getRequestURI();
		if (uri.endsWith("/error") || uri.endsWith("/login") || uri.endsWith("/user/register")
				|| uri.contains("/webjars/") || uri.contains("/resources/")) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}