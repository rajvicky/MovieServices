package com.javabeginnerstutorial.apigateway;

import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;

import org.slf4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulLoggingFilter extends ZuulFilter {
  private Logger logger=org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest request=RequestContext.getCurrentContext().getRequest();
		logger.info("reuest ->{} request uri->{}",request,request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
