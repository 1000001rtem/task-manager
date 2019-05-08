package ru.eremin.tm.server.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ru.eremin.tm.server.endpoint package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AccessForbiddenException_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "AccessForbiddenException");
    private final static QName _IncorrectDataException_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "IncorrectDataException");
    private final static QName _ServerDTO_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "serverDTO");
    private final static QName _ServerInfo_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "serverInfo");
    private final static QName _ServerInfoResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "serverInfoResponse");
    private final static QName _Session_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "session");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.eremin.tm.server.endpoint
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccessForbiddenException }
     */
    public AccessForbiddenException createAccessForbiddenException() {
        return new AccessForbiddenException();
    }

    /**
     * Create an instance of {@link IncorrectDataException }
     */
    public IncorrectDataException createIncorrectDataException() {
        return new IncorrectDataException();
    }

    /**
     * Create an instance of {@link ServerDTO }
     */
    public ServerDTO createServerDTO() {
        return new ServerDTO();
    }

    /**
     * Create an instance of {@link ServerInfo }
     */
    public ServerInfo createServerInfo() {
        return new ServerInfo();
    }

    /**
     * Create an instance of {@link ServerInfoResponse }
     */
    public ServerInfoResponse createServerInfoResponse() {
        return new ServerInfoResponse();
    }

    /**
     * Create an instance of {@link SessionDTO }
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccessForbiddenException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "AccessForbiddenException")
    public JAXBElement<AccessForbiddenException> createAccessForbiddenException(AccessForbiddenException value) {
        return new JAXBElement<AccessForbiddenException>(_AccessForbiddenException_QNAME, AccessForbiddenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncorrectDataException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "IncorrectDataException")
    public JAXBElement<IncorrectDataException> createIncorrectDataException(IncorrectDataException value) {
        return new JAXBElement<IncorrectDataException>(_IncorrectDataException_QNAME, IncorrectDataException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServerDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "serverDTO")
    public JAXBElement<ServerDTO> createServerDTO(ServerDTO value) {
        return new JAXBElement<ServerDTO>(_ServerDTO_QNAME, ServerDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServerInfo }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "serverInfo")
    public JAXBElement<ServerInfo> createServerInfo(ServerInfo value) {
        return new JAXBElement<ServerInfo>(_ServerInfo_QNAME, ServerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServerInfoResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "serverInfoResponse")
    public JAXBElement<ServerInfoResponse> createServerInfoResponse(ServerInfoResponse value) {
        return new JAXBElement<ServerInfoResponse>(_ServerInfoResponse_QNAME, ServerInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "session")
    public JAXBElement<SessionDTO> createSession(SessionDTO value) {
        return new JAXBElement<SessionDTO>(_Session_QNAME, SessionDTO.class, null, value);
    }

}
