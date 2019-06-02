package com.flinders.poc.interceptor;

import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.ldap.LoginBean;
import com.flinders.poc.util.AppUtil;

/**
 * HTTP URL Request Handler
 * @author mswahithali
 */

@Component
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

	@Value("${server.contextPath}")
	String rootUrl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Hong_Kong"));
		if (!request.getContextPath().equals(rootUrl) && !request.getRequestURI().equals(rootUrl+AppConstants.HOME_URL) && !request.getRequestURI().equals(rootUrl+AppConstants.LOGIN_AUTH_URL) && !request.getRequestURI().startsWith(rootUrl+AppConstants.RESOURCES_URL)) {
			Object object = request.getSession().getAttribute(AppConstants.LOGGED_IN_USER);
			if (object == null) {
				request.setAttribute(AppConstants.AUTH_ERROR_MSG, "invalid.session");
				AppUtil.log_debug("RequestHandlerInterceptor.preHandle", "Request Handler Failed");
				response.sendRedirect(rootUrl);
				return false;
			}
			if (object != null) {
				LoginBean loginBean = (LoginBean) object;
				MDC.put(AppConstants.LOGIN_ID, loginBean.getUserId());
			}
		}
		AppUtil.log_debug("RequestHandlerInterceptor.preHandle", "Request PreHandler Success");
		return true;
	}
}
