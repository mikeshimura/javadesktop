package com.mssoftech.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dbflute.cbean.result.ListResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.mssoftech.javadesktop.util.AppContextUtil;

public class ScreenSecurityUtil {
	static protected Logger log = LoggerFactory
			.getLogger(ScreenSecurityUtil.class);

	public static String generateAuthorizedScreen(HttpServletRequest request,
			HttpServletResponse response, String[] files, String[] libfiles,
			String title, AppContextUtil appContextUtil) {
		return generateAuthorizedScreen(request, response, files, libfiles,
				title, null, appContextUtil);
	}

	public static String generateAuthorizedScreen(HttpServletRequest request,
			HttpServletResponse response, String[] files, String[] libfiles,
			String title, String[] scripts, AppContextUtil appContextUtil) {
		String url[] = request.getRequestURL().toString().split("/");
		String screen = url[url.length - 1];


		JspUtil.setJspVariable(request, files, libfiles, title,
				scripts);
		return "index";
	}


}
