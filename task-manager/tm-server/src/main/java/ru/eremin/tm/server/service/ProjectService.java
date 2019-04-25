package ru.eremin.tm.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectRepository;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.repository.ProjectRepository;
import ru.eremin.tm.server.utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectService implements IProjectService {

    @Nullable
    private ITaskService taskService;

    public ProjectService(@Nullable final ITaskService taskService) {
        if (taskService == null) return;
        this.taskService = taskService;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findAll() {
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAll()
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final Project project = projectRepository.findOne(id);
        if (project == null) {
            connection.close();
            throw new IncorrectDataException("Wrong id");
        }
        connection.commit();
        connection.close();
        return new ProjectDTO(project);
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByUserId(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.persist(project);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void merge(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.merge(project);
        connection.commit();
        connection.close();
    }


    @Override
    @SneakyThrows(SQLException.class)
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.update(project);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        projectRepository.remove(id);
        taskService.removeAllTasksInProject(id);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        taskService.removeAll(userId);
        projectRepository.removeAll(userId);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final Project project = projectRepository.findOne(id);
        connection.commit();
        connection.close();
        return project != null;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByCreateDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStartDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByEndDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStatus(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByName(userId, name)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<ProjectDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByDescription(userId, description)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return projectDTOS;
    }

    @NotNull
    @Override
    public Project getEntity(@NotNull final ProjectDTO projectDTO) {
        @NotNull final Project project = new Project();
        project.setId(projectDTO.getId());
        if (projectDTO.getName() != null && !projectDTO.getName().isEmpty()) project.setName(projectDTO.getName());
        if (projectDTO.getDescription() != null && !projectDTO.getDescription().isEmpty()) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStartDate() != null) project.setStartDate(projectDTO.getStartDate());
        if (projectDTO.getEndDate() != null) project.setEndDate(projectDTO.getEndDate());
        if (projectDTO.getUserId() != null && !projectDTO.getUserId().isEmpty()) {
            project.setUserId(projectDTO.getUserId());
        }
        project.setStatus(projectDTO.getStatus());
        project.setCreateDate(projectDTO.getCreateDate());
        return project;
    }

}
