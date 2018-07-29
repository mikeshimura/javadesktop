package com.mssoftech.javadesktop.control;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mssoftech.web.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mssoftech.javadesktop.util.AppContextUtil;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexControl {
    final static Logger logger = LoggerFactory.getLogger(IndexControl.class);

    private AppContextUtil appContextUtil;

    public AppContextUtil getAppContextUtil() {
        return appContextUtil;
    }

    @Autowired
    public void setAppContextUtil(AppContextUtil appContextUtil) {
        this.appContextUtil = appContextUtil;
    }

    @RequestMapping("/")
    String index(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            HashMap jsCss = JspUtil.getJsCss(request.getContextPath(),
                    new String[]{"/js/lib/riot+compiler.js", "/js/lib/jquery.min.js",
                            "/js/lib/sweetalert/sweetalert.min.js", "/tag/tagcommon.js"},
                    new String[]{
                            },
                    new String[]{"/css/bootstrap.css", "/css/sweetalert.css", "/css/project.css"},
                    "JAVADESKTOP", new String[]{}, new String[]{"/tag/index.tag"});
            request.setAttribute("__jscss", jsCss);
            return "index";
        } catch (Exception e) {

            CommonUtil.putStacktraceToLog(logger, e);
            return "redirect:" + DBFluteUtil.getSystemErrorJspPath();
        }
    }
}
