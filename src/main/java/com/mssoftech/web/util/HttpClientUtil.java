package com.mssoftech.web.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;


public class HttpClientUtil {
    public static String get(String url) {
    	//System.out.println("url:"+url);
    	
        String res = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getMethod = new HttpGet(url);
            getMethod.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36");
            try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("res:"+res);
        return res;
    }

    public static HashMap post(String requestRrl, ArrayList<NameValuePair> params ) {
        //System.out.println("===== HTTP POST Start =====");
        HashMap rmap = new HashMap();
        String res = "";
        String code = "";
        try {
            URL url = new URL(requestRrl);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                StringBuilder postData = new StringBuilder();
                for (int i=0;i<params.size();i++) {
                	NameValuePair pair = params.get(i);
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(pair.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                connection.getOutputStream().write(postDataBytes);
                //System.out.print("getResponseCode:");
                int resc = connection.getResponseCode();
                //System.out.println(resc);

                code += "response " + String.valueOf(resc) + "\n";
                String resm = connection.getResponseMessage();
                code += resm;
               //System.out.println(resm);
                if (resc == HttpURLConnection.HTTP_OK) {
                    try {
                        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(isr);
                        String line;
                        while ((line = reader.readLine()) != null) {
                            //System.out.println(line);
                            res += line + "\r\n";
                        }
                    } catch (Exception e) {

                    }
                }
               // System.out.println(connection.getResponseMessage());

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("===== HTTP POST End =====");
        rmap.put("res", res);
        rmap.put("code", code);
        return rmap;
    }
    public static HashMap post(String requestRrl, String requestString) {
        //System.out.println("===== HTTP POST Start =====");
        HashMap rmap = new HashMap();
        String res = "";
        String code = "";
        try {
            URL url = new URL(requestRrl);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
   
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                BufferedWriter writer = new BufferedWriter(osw);
                writer.write(requestString);
                writer.flush();
                //System.out.print("getResponseCode:");
                int resc = connection.getResponseCode();
                //System.out.println(resc);

                code += "response " + String.valueOf(resc) + "\n";
                String resm = connection.getResponseMessage();
                code += resm;
               //System.out.println(resm);
                if (resc == HttpURLConnection.HTTP_OK) {
                    try {
                        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(isr);
                        String line;
                        while ((line = reader.readLine()) != null) {
                            //System.out.println(line);
                            res += line + "\r\n";
                        }
                    } catch (Exception e) {

                    }
                }
               // System.out.println(connection.getResponseMessage());

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("===== HTTP POST End =====");
        rmap.put("res", res);
        rmap.put("code", code);
        return rmap;
    }

    
    public static void getFile(String url, String filename) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getMethod = new HttpGet(url);
            getMethod.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36");
            try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
                HttpEntity entity = response.getEntity();
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(filename));
                entity.writeTo(output);
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    public static String getTrace(String url, String filename) {
    	   String res = "";
           try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
               HttpGet getMethod = new HttpGet(url);
               getMethod.setHeader("User-Agent",
                       "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36");
               try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
                   HttpEntity entity = response.getEntity();
                   res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
           if (filename!=null && filename.length()>0){
        	   FileUtil.writeFile(filename, res, "utf8");
           }
           return res;
    }


}
