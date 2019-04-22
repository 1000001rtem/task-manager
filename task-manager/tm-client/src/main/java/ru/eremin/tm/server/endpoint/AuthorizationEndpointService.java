package ru.eremin.tm.server.endpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-22T16:26:26.062+03:00
 * Generated source version: 3.2.7
 */
@WebServiceClient(name = "AuthorizationEndpointService",
        wsdlLocation = "http://localhost:8080/AuthorizationEndpoint?WSDL",
        targetNamespace = "http://endpoint.server.tm.eremin.ru/")
public class AuthorizationEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.server.tm.eremin.ru/", "AuthorizationEndpointService");
    public final static QName AuthorizationEndpointPort = new QName("http://endpoint.server.tm.eremin.ru/", "AuthorizationEndpointPort");

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/AuthorizationEndpoint?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AuthorizationEndpointService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "http://localhost:8080/AuthorizationEndpoint?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public AuthorizationEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AuthorizationEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuthorizationEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public AuthorizationEndpointService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AuthorizationEndpointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AuthorizationEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     * @return returns AuthorizationEndpoint
     */
    @WebEndpoint(name = "AuthorizationEndpointPort")
    public AuthorizationEndpoint getAuthorizationEndpointPort() {
        return super.getPort(AuthorizationEndpointPort, AuthorizationEndpoint.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns AuthorizationEndpoint
     */
    @WebEndpoint(name = "AuthorizationEndpointPort")
    public AuthorizationEndpoint getAuthorizationEndpointPort(WebServiceFeature... features) {
        return super.getPort(AuthorizationEndpointPort, AuthorizationEndpoint.class, features);
    }

}