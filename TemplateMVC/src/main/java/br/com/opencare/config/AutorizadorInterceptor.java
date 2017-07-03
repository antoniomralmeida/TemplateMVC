package br.com.opencare.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}

		String uri = request.getRequestURI();
		if (uri.startsWith("/error") || uri.startsWith("/user/postregister") || uri.startsWith("/user/register")
				|| uri.startsWith("/webjars") || uri.endsWith("login") || uri.endsWith("loginpost")) {
			return true;
		}

		response.sendRedirect("/user/login");
		return false;
	}
}