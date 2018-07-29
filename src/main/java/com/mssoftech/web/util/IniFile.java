package com.mssoftech.web.util;

import org.ini4j.Ini;

import java.io.*;

public class IniFile {

    public static Ini ini = null;
    public static String path;

    public static void store() throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("yahoo-rakuten.ini"), "utf8"));
        ini.store(out);
    }

    public static void setIni(String filename,String encoding) throws Exception {

        path = System.getProperty("user.dir");
        System.out.println("path:" + path);
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename),  encoding));
        ini = new Ini(in);

    }

}
