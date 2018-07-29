package com.mssoftech.javadesktop.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mssoftech.javadesktop.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mssoftech.javadesktop.util.AppContextUtil;
import com.mssoftech.web.util.ServiceUtil;

@RestController
@RequestMapping("/ajax")
public class AjaxControl {

	private AppContextUtil appContextUtil;

	public AppContextUtil getAppContextUtil() {
		return appContextUtil;
	}

	@Autowired
	public void setAppContextUtil(AppContextUtil appContextUtil) {
		this.appContextUtil = appContextUtil;
	}
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	String health() {

		return "OK";
	}
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	String test(HttpServletRequest request, HttpServletResponse response,
					 @RequestBody String str) {

		return ServiceUtil.invoke(str, request, response, "test",
				SeleniumService.class, appContextUtil);
	}
	@RequestMapping(value = "/quit", method = RequestMethod.POST)
	void quit(HttpServletRequest request, HttpServletResponse response,
					 @RequestBody String str) {
		System.exit(0);
	}

}
