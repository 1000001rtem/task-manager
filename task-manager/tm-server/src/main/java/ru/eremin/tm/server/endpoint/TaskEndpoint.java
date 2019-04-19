package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.ITaskEndpoint;
import ru.eremin.tm.server.exeption.BadRequestExeption;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.model.service.api.ITaskService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    @NotNull
    private ITaskService taskService;

    @Override
    @WebMethod
    public ResultDTO persistTask(@Nullable final Session session, @Nullable final TaskDTO task) {
        if (session == null || task == null) new ResultDTO(new BadRequestExeption());
        if (!checkSession(session)) new ResultDTO(new BadRequestExeption("wrong session"));
        taskService.persist(task);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<TaskDTO> findAllTasks(@Nullable final Session session) {
        if (session == null) return null;
        if (!checkSession(session)) return null;
        return taskService.findAll(session.getUserId());
    }

    @Override
    @WebMethod
    public TaskDTO findOneTask(@Nullable final Session session, @Nullable final String id) {
        if (session == null || id == null || id.isEmpty()) return null;
        if (!checkSession(session)) return null;
        return taskService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeTask(@Nullable final Session session, @Nullable final String id) throws IncorrectDataException {
        if (session == null || id == null || id.isEmpty()) new ResultDTO(new BadRequestExeption());
        if (!checkSession(session)) new ResultDTO(new BadRequestExeption("wrong session"));
        taskService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        taskService = locator.getTaskService();
        System.out.println("http://localhost:8080/TaskEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/TaskEndpoint", this);
    }

    @Override
    @WebMethod(exclude = true)
    public boolean checkSession(@NotNull final Session session) {
        return session.getSign().equals(locator.getSession().getSign());
    }

}
