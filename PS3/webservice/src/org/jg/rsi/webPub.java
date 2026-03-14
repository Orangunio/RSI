package org.jg.rsi;

import javax.xml.ws.Endpoint;

public class webPub {
    public static void main(String[] args) {
        // Publikowanie serwisu lokalnie do celów testowych [cite: 105]
        Endpoint.publish("http://localhost:9999/ws/hello", new web());
        System.out.println("Web serwis czeka na klienta....");
    }
}