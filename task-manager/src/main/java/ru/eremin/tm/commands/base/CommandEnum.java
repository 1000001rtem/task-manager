package ru.eremin.tm.commands.base;

import lombok.Getter;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
public enum CommandEnum {

    HELP("help", "Show all commands"),
    EXIT("exit", "Exit"),
    AUTHORIZATION("auth", "Authorization"),
    LOGOUT("logout", "Logout"),

    DATA_BEAN_LOAD("data_bean_load", "Load data from binary file"),
    DATA_BEAN_SAVE("data_bean_save", "Save data to binary file"),
    DATA_BEAN_CLEAN("data_bean_clean", "Remove binary file"),

    PROJECT_CREATE("project_create", "Create new project"),
    PROJECT_LIST("project_list", "Show all Projects"),
    PROJECT_INFO("project_info", "Show project information"),
    PROJECT_REMOVE("project_remove", "Remove selected project"),
    PROJECT_CLEAR("project_clear", "Remove all project"),

    TASK_CREATE("task_create", "Create new task"),
    TASK_LIST("task_list", "Show all tasks"),
    TASK_INFO("task_info", "Show task information"),
    TASK_IN_PROJECT("task_in_project", "Show all tasks in selected project"),
    TASK_REMOVE("task_remove", "Remove selected task"),
    TASK_CLEAR("task_clear", "Remove all task"),

    USER_REGISTRATION("registration_user", "Registration new user"),
    USER_CHANGE_PASSWORD("user_change_password", "Change password"),
    USER_INFO("user_info", "Show profile information"),
    USER_UPDATE("user_update", "Update profile data");

    private String name;

    private String description;

    CommandEnum(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
