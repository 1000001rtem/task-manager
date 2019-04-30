package ru.eremin.tm.server.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.3
 * 2019-04-30T17:33:09.680+03:00
 * Generated source version: 3.2.3
 */
@WebService(targetNamespace = "http://endpoint.server.tm.eremin.ru/", name = "SessionEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/updateSessionRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/updateSessionResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/updateSession/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/updateSession/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "updateSession", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.UpdateSession")
    @ResponseWrapper(localName = "updateSessionResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.UpdateSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.eremin.tm.server.endpoint.ResultDTO updateSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg1
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findAllSessionsRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findAllSessionsResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findAllSessions/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findAllSessions/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "findAllSessions", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindAllSessions")
    @ResponseWrapper(localName = "findAllSessionsResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindAllSessionsResponse")
    @WebResult(name = "return", targetNamespace = "")
    java.util.List<ru.eremin.tm.server.endpoint.SessionDTO> findAllSessions(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeAllSessionsRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeAllSessionsResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeAllSessions/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeAllSessions/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "removeAllSessions", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveAllSessions")
    @ResponseWrapper(localName = "removeAllSessionsResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveAllSessionsResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.eremin.tm.server.endpoint.ResultDTO removeAllSessions(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/persistSessionRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/persistSessionResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/persistSession/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/persistSession/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "persistSession", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.PersistSession")
    @ResponseWrapper(localName = "persistSessionResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.PersistSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.eremin.tm.server.endpoint.ResultDTO persistSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg1
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findOneSessionRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findOneSessionResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findOneSession/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/findOneSession/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "findOneSession", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindOneSession")
    @ResponseWrapper(localName = "findOneSessionResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindOneSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.eremin.tm.server.endpoint.SessionDTO findOneSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeSessionRequest", output = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeSessionResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeSession/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/SessionEndpoint/removeSession/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "removeSession", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveSession")
    @ResponseWrapper(localName = "removeSessionResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.eremin.tm.server.endpoint.ResultDTO removeSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.eremin.tm.server.endpoint.SessionDTO arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;
}
