
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
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AccessForbiddenException_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "AccessForbiddenException");
    private final static QName _IncorrectDataException_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "IncorrectDataException");
    private final static QName _FindAllSessions_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findAllSessions");
    private final static QName _FindAllSessionsResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findAllSessionsResponse");
    private final static QName _FindOneSession_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneSession");
    private final static QName _FindOneSessionResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneSessionResponse");
    private final static QName _PersistSession_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "persistSession");
    private final static QName _PersistSessionResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "persistSessionResponse");
    private final static QName _RemoveAllSessions_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeAllSessions");
    private final static QName _RemoveAllSessionsResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeAllSessionsResponse");
    private final static QName _RemoveSession_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeSession");
    private final static QName _RemoveSessionResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeSessionResponse");
    private final static QName _ResultDTO_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "resultDTO");
    private final static QName _Session_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "session");
    private final static QName _UpdateSession_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "updateSession");
    private final static QName _UpdateSessionResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "updateSessionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.eremin.tm.server.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccessForbiddenException }
     * 
     */
    public AccessForbiddenException createAccessForbiddenException() {
        return new AccessForbiddenException();
    }

    /**
     * Create an instance of {@link IncorrectDataException }
     * 
     */
    public IncorrectDataException createIncorrectDataException() {
        return new IncorrectDataException();
    }

    /**
     * Create an instance of {@link FindAllSessions }
     * 
     */
    public FindAllSessions createFindAllSessions() {
        return new FindAllSessions();
    }

    /**
     * Create an instance of {@link FindAllSessionsResponse }
     * 
     */
    public FindAllSessionsResponse createFindAllSessionsResponse() {
        return new FindAllSessionsResponse();
    }

    /**
     * Create an instance of {@link FindOneSession }
     * 
     */
    public FindOneSession createFindOneSession() {
        return new FindOneSession();
    }

    /**
     * Create an instance of {@link FindOneSessionResponse }
     * 
     */
    public FindOneSessionResponse createFindOneSessionResponse() {
        return new FindOneSessionResponse();
    }

    /**
     * Create an instance of {@link PersistSession }
     * 
     */
    public PersistSession createPersistSession() {
        return new PersistSession();
    }

    /**
     * Create an instance of {@link PersistSessionResponse }
     * 
     */
    public PersistSessionResponse createPersistSessionResponse() {
        return new PersistSessionResponse();
    }

    /**
     * Create an instance of {@link RemoveAllSessions }
     * 
     */
    public RemoveAllSessions createRemoveAllSessions() {
        return new RemoveAllSessions();
    }

    /**
     * Create an instance of {@link RemoveAllSessionsResponse }
     * 
     */
    public RemoveAllSessionsResponse createRemoveAllSessionsResponse() {
        return new RemoveAllSessionsResponse();
    }

    /**
     * Create an instance of {@link RemoveSession }
     * 
     */
    public RemoveSession createRemoveSession() {
        return new RemoveSession();
    }

    /**
     * Create an instance of {@link RemoveSessionResponse }
     * 
     */
    public RemoveSessionResponse createRemoveSessionResponse() {
        return new RemoveSessionResponse();
    }

    /**
     * Create an instance of {@link ResultDTO }
     * 
     */
    public ResultDTO createResultDTO() {
        return new ResultDTO();
    }

    /**
     * Create an instance of {@link SessionDTO }
     * 
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link UpdateSession }
     * 
     */
    public UpdateSession createUpdateSession() {
        return new UpdateSession();
    }

    /**
     * Create an instance of {@link UpdateSessionResponse }
     * 
     */
    public UpdateSessionResponse createUpdateSessionResponse() {
        return new UpdateSessionResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccessForbiddenException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "AccessForbiddenException")
    public JAXBElement<AccessForbiddenException> createAccessForbiddenException(AccessForbiddenException value) {
        return new JAXBElement<AccessForbiddenException>(_AccessForbiddenException_QNAME, AccessForbiddenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncorrectDataException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "IncorrectDataException")
    public JAXBElement<IncorrectDataException> createIncorrectDataException(IncorrectDataException value) {
        return new JAXBElement<IncorrectDataException>(_IncorrectDataException_QNAME, IncorrectDataException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllSessions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findAllSessions")
    public JAXBElement<FindAllSessions> createFindAllSessions(FindAllSessions value) {
        return new JAXBElement<FindAllSessions>(_FindAllSessions_QNAME, FindAllSessions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllSessionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findAllSessionsResponse")
    public JAXBElement<FindAllSessionsResponse> createFindAllSessionsResponse(FindAllSessionsResponse value) {
        return new JAXBElement<FindAllSessionsResponse>(_FindAllSessionsResponse_QNAME, FindAllSessionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneSession")
    public JAXBElement<FindOneSession> createFindOneSession(FindOneSession value) {
        return new JAXBElement<FindOneSession>(_FindOneSession_QNAME, FindOneSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneSessionResponse")
    public JAXBElement<FindOneSessionResponse> createFindOneSessionResponse(FindOneSessionResponse value) {
        return new JAXBElement<FindOneSessionResponse>(_FindOneSessionResponse_QNAME, FindOneSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "persistSession")
    public JAXBElement<PersistSession> createPersistSession(PersistSession value) {
        return new JAXBElement<PersistSession>(_PersistSession_QNAME, PersistSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "persistSessionResponse")
    public JAXBElement<PersistSessionResponse> createPersistSessionResponse(PersistSessionResponse value) {
        return new JAXBElement<PersistSessionResponse>(_PersistSessionResponse_QNAME, PersistSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllSessions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeAllSessions")
    public JAXBElement<RemoveAllSessions> createRemoveAllSessions(RemoveAllSessions value) {
        return new JAXBElement<RemoveAllSessions>(_RemoveAllSessions_QNAME, RemoveAllSessions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllSessionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeAllSessionsResponse")
    public JAXBElement<RemoveAllSessionsResponse> createRemoveAllSessionsResponse(RemoveAllSessionsResponse value) {
        return new JAXBElement<RemoveAllSessionsResponse>(_RemoveAllSessionsResponse_QNAME, RemoveAllSessionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeSession")
    public JAXBElement<RemoveSession> createRemoveSession(RemoveSession value) {
        return new JAXBElement<RemoveSession>(_RemoveSession_QNAME, RemoveSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeSessionResponse")
    public JAXBElement<RemoveSessionResponse> createRemoveSessionResponse(RemoveSessionResponse value) {
        return new JAXBElement<RemoveSessionResponse>(_RemoveSessionResponse_QNAME, RemoveSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "resultDTO")
    public JAXBElement<ResultDTO> createResultDTO(ResultDTO value) {
        return new JAXBElement<ResultDTO>(_ResultDTO_QNAME, ResultDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "session")
    public JAXBElement<SessionDTO> createSession(SessionDTO value) {
        return new JAXBElement<SessionDTO>(_Session_QNAME, SessionDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "updateSession")
    public JAXBElement<UpdateSession> createUpdateSession(UpdateSession value) {
        return new JAXBElement<UpdateSession>(_UpdateSession_QNAME, UpdateSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "updateSessionResponse")
    public JAXBElement<UpdateSessionResponse> createUpdateSessionResponse(UpdateSessionResponse value) {
        return new JAXBElement<UpdateSessionResponse>(_UpdateSessionResponse_QNAME, UpdateSessionResponse.class, null, value);
    }

}
