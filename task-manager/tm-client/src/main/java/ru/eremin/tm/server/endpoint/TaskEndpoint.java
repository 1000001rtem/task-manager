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
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-22T16:26:25.877+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.server.tm.eremin.ru/", name = "TaskEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface TaskEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findAllTasksRequest", output = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findAllTasksResponse", fault = {@FaultAction(className = SessionValidateExeption_Exception.class, value = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findAllTasks/Fault/SessionValidateExeption")})
    @RequestWrapper(localName = "findAllTasks", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindAllTasks")
    @ResponseWrapper(localName = "findAllTasksResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindAllTasksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.eremin.tm.server.endpoint.TaskDTO> findAllTasks(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0
    ) throws SessionValidateExeption_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/removeTaskRequest", output = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/removeTaskResponse", fault = {@FaultAction(className = SessionValidateExeption_Exception.class, value = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/removeTask/Fault/SessionValidateExeption"), @FaultAction(className = IncorrectDataException_Exception.class, value = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/removeTask/Fault/IncorrectDataException")})
    @RequestWrapper(localName = "removeTask", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveTask")
    @ResponseWrapper(localName = "removeTaskResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.RemoveTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.ResultDTO removeTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws SessionValidateExeption_Exception, IncorrectDataException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/persistTaskRequest", output = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/persistTaskResponse", fault = {@FaultAction(className = SessionValidateExeption_Exception.class, value = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/persistTask/Fault/SessionValidateExeption")})
    @RequestWrapper(localName = "persistTask", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.PersistTask")
    @ResponseWrapper(localName = "persistTaskResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.PersistTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.ResultDTO persistTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.eremin.tm.server.endpoint.TaskDTO arg1
    ) throws SessionValidateExeption_Exception;

    @WebMethod
    @Action(input = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findOneTaskRequest", output = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findOneTaskResponse", fault = {@FaultAction(className = SessionValidateExeption_Exception.class, value = "http://endpoint.server.tm.eremin.ru/TaskEndpoint/findOneTask/Fault/SessionValidateExeption")})
    @RequestWrapper(localName = "findOneTask", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindOneTask")
    @ResponseWrapper(localName = "findOneTaskResponse", targetNamespace = "http://endpoint.server.tm.eremin.ru/", className = "ru.eremin.tm.server.endpoint.FindOneTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.eremin.tm.server.endpoint.TaskDTO findOneTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.eremin.tm.server.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws SessionValidateExeption_Exception;
}
