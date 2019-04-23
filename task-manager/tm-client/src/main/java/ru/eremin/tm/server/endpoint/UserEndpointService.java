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
 * 2019-04-23T20:29:11.386+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "UserEndpointService",
        wsdlLocation = "http://localhost:8080/UserEndpoint?WSDL",
        targetNamespace = "http://endpoint.server.tm.eremin.ru/")
public class UserEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.server.tm.eremin.ru/", "UserEndpointService");
    public final static QName UserEndpointPort = new QName("http://endpoint.server.tm.eremin.ru/", "UserEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/UserEndpoint?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserEndpointService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "http://localhost:8080/UserEndpoint?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public UserEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserEndpointService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserEndpointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     *
     * @return
     *     returns UserEndpoint
     */
    @WebEndpoint(name = "UserEndpointPort")
    public UserEndpoint getUserEndpointPort() {
        return super.getPort(UserEndpointPort, UserEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserEndpoint
     */
    @WebEndpoint(name = "UserEndpointPort")
    public UserEndpoint getUserEndpointPort(WebServiceFeature... features) {
        return super.getPort(UserEndpointPort, UserEndpoint.class, features);
    }

}
