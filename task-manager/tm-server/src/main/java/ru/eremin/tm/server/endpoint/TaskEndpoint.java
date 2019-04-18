package ru.eremin.tm.server.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.repository.TaskRepository;
import ru.eremin.tm.server.model.repository.api.ITaskRepository;
import ru.eremin.tm.server.model.service.TaskService;
import ru.eremin.tm.server.model.service.api.ITaskService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class TaskEndpoint extends AbstractEndpoint {

    @NotNull
    private ITaskService taskService;

    @WebMethod
    public void persist(final TaskDTO task) {
        if (task != null) taskService.persist(task);
    }

    @WebMethod
    public List<TaskDTO> findAll(){
        return taskService.findAll("123");
    }

    @WebMethod
    public TaskDTO findOne(@Nullable final String id){
        if(id == null || id.isEmpty()) return null;
        return taskService.findOne(id);
    }

    @WebMethod
    public void delete(@Nullable final String id) throws IncorrectDataException {
        if(id == null || id.isEmpty()) return;
        taskService.remove(id);
    }

    @Override
    public void init() {
        taskService = locator.getTaskService();
        System.out.println("http://localhost:8080/TaskEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/TaskEndpoint", this);
    }

}
