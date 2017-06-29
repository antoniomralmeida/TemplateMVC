package br.com.opencare.spring;

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
		if (uri.startsWith("/webjars") || uri.endsWith("login") || uri.endsWith("loginpost")) {
			return true;
		}

		response.sendRedirect("user/login");
		return false;
	}
}