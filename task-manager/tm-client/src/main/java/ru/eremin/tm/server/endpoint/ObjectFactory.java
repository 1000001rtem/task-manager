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
    private final static QName _ClearJSON_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "clearJSON");
    private final static QName _ClearJSONResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "clearJSONResponse");
    private final static QName _LoadJSON_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "loadJSON");
    private final static QName _LoadJSONResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "loadJSONResponse");
    private final static QName _ResultDTO_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "resultDTO");
    private final static QName _SaveJSON_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "saveJSON");
    private final static QName _SaveJSONResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "saveJSONResponse");
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
     * Create an instance of {@link ClearJSON }
     */
    public ClearJSON createClearJSON() {
        return new ClearJSON();
    }

    /**
     * Create an instance of {@link ClearJSONResponse }
     */
    public ClearJSONResponse createClearJSONResponse() {
        return new ClearJSONResponse();
    }

    /**
     * Create an instance of {@link LoadJSON }
     */
    public LoadJSON createLoadJSON() {
        return new LoadJSON();
    }

    /**
     * Create an instance of {@link LoadJSONResponse }
     */
    public LoadJSONResponse createLoadJSONResponse() {
        return new LoadJSONResponse();
    }

    /**
     * Create an instance of {@link ResultDTO }
     */
    public ResultDTO createResultDTO() {
        return new ResultDTO();
    }

    /**
     * Create an instance of {@link SaveJSON }
     */
    public SaveJSON createSaveJSON() {
        return new SaveJSON();
    }

    /**
     * Create an instance of {@link SaveJSONResponse }
     */
    public SaveJSONResponse createSaveJSONResponse() {
        return new SaveJSONResponse();
    }

    /**
     * Create an instance of {@link SessionDTO }
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Throwable }
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearJSON }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "clearJSON")
    public JAXBElement<ClearJSON> createClearJSON(ClearJSON value) {
        return new JAXBElement<ClearJSON>(_ClearJSON_QNAME, ClearJSON.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearJSONResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "clearJSONResponse")
    public JAXBElement<ClearJSONResponse> createClearJSONResponse(ClearJSONResponse value) {
        return new JAXBElement<ClearJSONResponse>(_ClearJSONResponse_QNAME, ClearJSONResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJSON }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "loadJSON")
    public JAXBElement<LoadJSON> createLoadJSON(LoadJSON value) {
        return new JAXBElement<LoadJSON>(_LoadJSON_QNAME, LoadJSON.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJSONResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "loadJSONResponse")
    public JAXBElement<LoadJSONResponse> createLoadJSONResponse(LoadJSONResponse value) {
        return new JAXBElement<LoadJSONResponse>(_LoadJSONResponse_QNAME, LoadJSONResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "resultDTO")
    public JAXBElement<ResultDTO> createResultDTO(ResultDTO value) {
        return new JAXBElement<ResultDTO>(_ResultDTO_QNAME, ResultDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJSON }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "saveJSON")
    public JAXBElement<SaveJSON> createSaveJSON(SaveJSON value) {
        return new JAXBElement<SaveJSON>(_SaveJSON_QNAME, SaveJSON.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJSONResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "saveJSONResponse")
    public JAXBElement<SaveJSONResponse> createSaveJSONResponse(SaveJSONResponse value) {
        return new JAXBElement<SaveJSONResponse>(_SaveJSONResponse_QNAME, SaveJSONResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "session")
    public JAXBElement<SessionDTO> createSession(SessionDTO value) {
        return new JAXBElement<SessionDTO>(_Session_QNAME, SessionDTO.class, null, value);
    }

}
