//package com.example.demo.util.swagger;
//
//
//import java.awt.*;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.UnknownHostException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class SwaggerUiOpener {
//
//    @Autowired
//    Environment environment;
//
//    @EventListener({ApplicationReadyEvent.class})
//    void applicationReadyEvent() throws UnknownHostException {
//        System.out.println("Application started ... launching browser now");
//        String port = environment.getProperty("local.server.port", "8080");
//        String host = environment.getProperty("server.address", "localhost");
//        String hostPort = "http://" + host + ":" + port + "/swagger-ui/index.html";
//        browse(hostPort);
//    }
//
//    public static void browse(String url) {
//        if(Desktop.isDesktopSupported()){
//            Desktop desktop = Desktop.getDesktop();
//            try {
//                desktop.browse(new URI(url));
//            } catch (IOException | URISyntaxException e) {
//                e.printStackTrace();
//            }
//        }else{
//            Runtime runtime = Runtime.getRuntime();
//            try {
//                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}