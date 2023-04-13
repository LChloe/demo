package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Component
public class InstantiationTracingServletProcessor implements ApplicationListener<WebServerInitializedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationTracingServletProcessor.class);

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int serverPort = event.getWebServer().getPort();
        String serverHost = "127.0.0.1";
        try {
            serverHost = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //e.printStackTrace();
        }
        printKeyLoadMessage(serverHost, serverPort);
    }



    public static boolean printKeyLoadMessage(String serverHost, int serverPort){
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    Server  api  is  ").append(serverHost).append(":").append(serverPort);
        sb.append("\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }

}
