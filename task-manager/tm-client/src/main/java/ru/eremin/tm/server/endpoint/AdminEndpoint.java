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
 * 2019-05-08T13:48:32.793+03:00
 * Generated source version: 3.2.3
 *
 */
@WebService(targetNamespace = "http://endpoint.server.tm.eremin.ru/", name = "AdminEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface AdminEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/loadJSONRequest", output = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/loadJSONResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/loadJSON/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/loadJSON/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "loadJSON", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.LoadJSON")
    @ResponseWrapper(localName = "loadJSONResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.LoadJSONResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.ResultDTO loadJSON(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/clearJSONRequest", output = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/clearJSONResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/clearJSON/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/clearJSON/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "clearJSON", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.ClearJSON")
    @ResponseWrapper(localName = "clearJSONResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.ClearJSONResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.ResultDTO clearJSON(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/sessionValidateRequest", output = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/sessionValidateResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/sessionValidate/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/sessionValidate/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "sessionValidate", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.SessionValidate")
    @ResponseWrapper(localName = "sessionValidateResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.SessionValidateResponse")
    public void sessionValidate(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/saveJSONRequest", output = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/saveJSONResponse", fault = {@FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/saveJSON/Fault/IncorrectDataException"), @FaultAction(className = AccessForbiddenException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/AdminEndpoint/saveJSON/Fault/AccessForbiddenException")})
    @RequestWrapper(localName = "saveJSON", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.SaveJSON")
    @ResponseWrapper(localName = "saveJSONResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.SaveJSONResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.ResultDTO saveJSON(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws IncorrectDataException_Exception, AccessForbiddenException_Exception;
}
