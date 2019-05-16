package ru.eremin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.IncorrectDataException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter")
public class TaskRemoveController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @GetMapping(value = "/task-remove")
    public String taskRemove(@RequestParam("id") @Nullable final String id) throws IncorrectDataException {
        taskService.remove(id);
        return "redirect:/enter/task-list";
    }

}
