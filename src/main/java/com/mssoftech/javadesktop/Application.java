package com.mssoftech.javadesktop;

import com.mssoftech.javadesktop.service.SeleniumService;
import com.mssoftech.web.util.IniFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.time.LocalDateTime;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan
public class Application {
    static protected Logger log = LoggerFactory.getLogger(Application.class);
    public static AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = null;
    public static WebDriver driver;
    public static String[] wid = new String[]{"", "", ""};
    public static Double usd = 0.0;
    public static LocalDateTime lastGetUsd = null;
    public static String TEST = "";
    public static String port;
    public static String url;
    public static String host;

    public static void main(String[] args) throws Exception {
        IniFile.setIni("desktop.ini", "utf8");
        Application.port = IniFile.ini.get("common", "PORT");
        Application.host = IniFile.ini.get("common", "HOST");
        if (Application.host == null || Application.host.length() == 0) {
            Application.host = "localhost";
        }
        if (SeleniumService.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        port = System.getenv("PORT");
        if (port != null) {
            log.debug("BINDING PORT:" + port);
        } else {
            port = "8080";
            System.setProperty("PORT", "8080");
        }
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        resolver.setPrefix("/WEB-INF/views/");
        return resolver;
    }

}
