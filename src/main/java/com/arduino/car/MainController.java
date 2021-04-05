package com.arduino.car;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.http.client.methods.HttpGet;


    @RequestMapping("control")
    @RestController
    @CrossOrigin(origins = "*", maxAge = 3600)
    public class MainController {
        private final static CloseableHttpClient httpClient = HttpClients.createDefault();

        @GetMapping("move")
            public String move(String command, String value ) throws Exception {
            HttpGet request = new HttpGet("http://192.168.178.56");

            request = new HttpGet("http://192.168.178.56/" + command+"?value="+Integer.parseInt(value));
//          request = new HttpGet("http://85d519b77517.ngrok.io/" + command+"?value="+Integer.parseInt(value));

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Get HttpResponse Status
                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();
                System.out.println(headers);

                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } catch (Exception ex) {

            }
            return "";
        }




    }


