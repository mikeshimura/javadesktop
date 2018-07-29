package com.mssoftech.javadesktop.service;

import com.mssoftech.javadesktop.Application;
import com.mssoftech.web.util.DBFluteUtil;
import com.mssoftech.web.util.HttpClientUtil;
import com.mssoftech.web.util.SeleniumUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SeleniumService {
    ExecutorService exec = Executors.newFixedThreadPool(4);
    @PostConstruct
    public void initAfterStartup() {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                startup();
            }

        });


    }
    public void startup() {
        try {
            Application.driver.get("https://www.google.co.jp");
            String url = "http://localhost:" + Application.port + "/";
            String res = "";
            while (res.length() == 0) {
                String urlx = url + "ajax/health";
                res = HttpClientUtil.get(urlx);
                Thread.sleep(1000);
            }
            Application.driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HashMap test(HashMap params, HttpServletRequest request,
                                HttpServletResponse response) {
        String in= (String) params.get("in");
        return DBFluteUtil.setErrorMessage(in+" was sent.",params);
    }

    public static boolean isWindows() {
        String OS_NAME = System.getProperty("os.name").toLowerCase();
        return OS_NAME.startsWith("windows");
    }
}
