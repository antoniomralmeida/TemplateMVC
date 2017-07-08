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
		System.out.println(uri);
		if (uri.endsWith("/errors") || uri.endsWith("/login") || uri.endsWith("/user/postregister")
				|| uri.endsWith("/user/register") || uri.contains("/webjars/") || uri.contains("/resources/")) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}