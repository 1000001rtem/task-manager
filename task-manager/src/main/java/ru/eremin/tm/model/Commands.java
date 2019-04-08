package ru.eremin.tm.model;

import lombok.Getter;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
public enum Commands {

    HELP("help", "Show all commands"),
    EXIT("exit", "Exit"),

    DATA_BEAN_LOAD("data_bean_load", "Load data from binary file"),
    DATA_BEAN_SAVE("data_bean_save", "Save data to binary file"),
    DATA_BEAN_CLEAN("data_bean_clean", "Remove binary file"),

    PROJECT_CREATE("project_create", "Create new project"),
    PROJECT_LIST("project_list", "Show all Projects"),
    PROJECT_REMOVE("project_remove", "Remove selected project"),
    PROJECT_CLEAN("project_clean", "Remove all project"),

    TASK_CREATE("task_create", "Create new task"),
    TASK_LIST("task_list", "Show all tasks"),
    TASK_REMOVE("task_remove", "Remove selected task"),
    TASK_CLEAN("task_clean", "Remove all task");

    private String command;

    private String description;

    Commands(final String command, final String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getCommand();
    }
}
