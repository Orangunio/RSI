package org.jg.rsi;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

// Service Endpoint Interface [cite: 44]
@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface webint {
    @WebMethod
    String getHelloWorldAsString(String name);
}