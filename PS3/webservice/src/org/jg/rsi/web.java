package org.jg.rsi;

import javax.jws.WebService;

@WebService(
        endpointInterface = "org.jg.rsi.webint",
        targetNamespace = "http://moj.serwis.pl/",
        serviceName = "webService",
        portName = "webPort"
)
public class web implements webint {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Witaj świecie JAX-WS: " + name;
    }
}