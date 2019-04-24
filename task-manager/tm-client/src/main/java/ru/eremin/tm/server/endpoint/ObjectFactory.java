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
    private final static QName _ChangeUserPassword_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "changeUserPassword");
    private final static QName _ChangeUserPasswordResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "changeUserPasswordResponse");
    private final static QName _FindAllUsers_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findAllUsers");
    private final static QName _FindAllUsersResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findAllUsersResponse");
    private final static QName _FindOneUser_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneUser");
    private final static QName _FindOneUserByLogin_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneUserByLogin");
    private final static QName _FindOneUserByLoginResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneUserByLoginResponse");
    private final static QName _FindOneUserResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "findOneUserResponse");
    private final static QName _PersistUser_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "persistUser");
    private final static QName _PersistUserResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "persistUserResponse");
    private final static QName _RemoveUser_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "removeUserResponse");
    private final static QName _ResultDTO_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "resultDTO");
    private final static QName _Session_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "session");
    private final static QName _UpdateUser_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "updateUser");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://endpoint.server.tm.eremin.ru/", "updateUserResponse");

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
     * Create an instance of {@link ChangeUserPassword }
     */
    public ChangeUserPassword createChangeUserPassword() {
        return new ChangeUserPassword();
    }

    /**
     * Create an instance of {@link ChangeUserPasswordResponse }
     */
    public ChangeUserPasswordResponse createChangeUserPasswordResponse() {
        return new ChangeUserPasswordResponse();
    }

    /**
     * Create an instance of {@link FindAllUsers }
     */
    public FindAllUsers createFindAllUsers() {
        return new FindAllUsers();
    }

    /**
     * Create an instance of {@link FindAllUsersResponse }
     */
    public FindAllUsersResponse createFindAllUsersResponse() {
        return new FindAllUsersResponse();
    }

    /**
     * Create an instance of {@link FindOneUser }
     */
    public FindOneUser createFindOneUser() {
        return new FindOneUser();
    }

    /**
     * Create an instance of {@link FindOneUserByLogin }
     */
    public FindOneUserByLogin createFindOneUserByLogin() {
        return new FindOneUserByLogin();
    }

    /**
     * Create an instance of {@link FindOneUserByLoginResponse }
     */
    public FindOneUserByLoginResponse createFindOneUserByLoginResponse() {
        return new FindOneUserByLoginResponse();
    }

    /**
     * Create an instance of {@link FindOneUserResponse }
     */
    public FindOneUserResponse createFindOneUserResponse() {
        return new FindOneUserResponse();
    }

    /**
     * Create an instance of {@link PersistUser }
     */
    public PersistUser createPersistUser() {
        return new PersistUser();
    }

    /**
     * Create an instance of {@link PersistUserResponse }
     */
    public PersistUserResponse createPersistUserResponse() {
        return new PersistUserResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link ResultDTO }
     */
    public ResultDTO createResultDTO() {
        return new ResultDTO();
    }

    /**
     * Create an instance of {@link SessionDTO }
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link UpdateUser }
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link UserDTO }
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeUserPassword }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "changeUserPassword")
    public JAXBElement<ChangeUserPassword> createChangeUserPassword(ChangeUserPassword value) {
        return new JAXBElement<ChangeUserPassword>(_ChangeUserPassword_QNAME, ChangeUserPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeUserPasswordResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "changeUserPasswordResponse")
    public JAXBElement<ChangeUserPasswordResponse> createChangeUserPasswordResponse(ChangeUserPasswordResponse value) {
        return new JAXBElement<ChangeUserPasswordResponse>(_ChangeUserPasswordResponse_QNAME, ChangeUserPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsers }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findAllUsers")
    public JAXBElement<FindAllUsers> createFindAllUsers(FindAllUsers value) {
        return new JAXBElement<FindAllUsers>(_FindAllUsers_QNAME, FindAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findAllUsersResponse")
    public JAXBElement<FindAllUsersResponse> createFindAllUsersResponse(FindAllUsersResponse value) {
        return new JAXBElement<FindAllUsersResponse>(_FindAllUsersResponse_QNAME, FindAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUser }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneUser")
    public JAXBElement<FindOneUser> createFindOneUser(FindOneUser value) {
        return new JAXBElement<FindOneUser>(_FindOneUser_QNAME, FindOneUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserByLogin }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneUserByLogin")
    public JAXBElement<FindOneUserByLogin> createFindOneUserByLogin(FindOneUserByLogin value) {
        return new JAXBElement<FindOneUserByLogin>(_FindOneUserByLogin_QNAME, FindOneUserByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserByLoginResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneUserByLoginResponse")
    public JAXBElement<FindOneUserByLoginResponse> createFindOneUserByLoginResponse(FindOneUserByLoginResponse value) {
        return new JAXBElement<FindOneUserByLoginResponse>(_FindOneUserByLoginResponse_QNAME, FindOneUserByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "findOneUserResponse")
    public JAXBElement<FindOneUserResponse> createFindOneUserResponse(FindOneUserResponse value) {
        return new JAXBElement<FindOneUserResponse>(_FindOneUserResponse_QNAME, FindOneUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistUser }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "persistUser")
    public JAXBElement<PersistUser> createPersistUser(PersistUser value) {
        return new JAXBElement<PersistUser>(_PersistUser_QNAME, PersistUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "persistUserResponse")
    public JAXBElement<PersistUserResponse> createPersistUserResponse(PersistUserResponse value) {
        return new JAXBElement<PersistUserResponse>(_PersistUserResponse_QNAME, PersistUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "resultDTO")
    public JAXBElement<ResultDTO> createResultDTO(ResultDTO value) {
        return new JAXBElement<ResultDTO>(_ResultDTO_QNAME, ResultDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionDTO }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "session")
    public JAXBElement<SessionDTO> createSession(SessionDTO value) {
        return new JAXBElement<SessionDTO>(_Session_QNAME, SessionDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.server.tm.eremin.ru/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

}
