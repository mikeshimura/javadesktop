package com.mssoftech.web.util;

import com.mssoftech.javadesktop.Application;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class SeleniumUtil {
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    public static void waitForPageLoaded() throws Exception {
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(Application.driver, 30);

           String comp= (((JavascriptExecutor) Application.driver).executeScript("return document.readyState")).toString();
           while(!comp.equals("complete")){
               Thread.sleep(500);
               comp= (((JavascriptExecutor) Application.driver).executeScript("return document.readyState")).toString();
           }

        } catch (Throwable error) {
            throw new Exception("Timeout waiting for Page Load Request to complete.");
        }
    }
}
