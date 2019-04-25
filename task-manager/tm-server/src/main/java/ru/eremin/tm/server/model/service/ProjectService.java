package ru.eremin.tm.server.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.repository.api.IProjectRepository;
import ru.eremin.tm.server.model.service.api.IProjectService;
import ru.eremin.tm.server.model.service.api.ITaskService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectService implements IProjectService {

    @Nullable
    private IProjectRepository projectRepository;

    @Nullable
    private ITaskService taskService;

    public ProjectService(@Nullable final IProjectRepository projectRepository, @Nullable final ITaskService taskService) {
        if (projectRepository == null || taskService == null) return;
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Project project = projectRepository.findOne(id);
        if (project == null) throw new IncorrectDataException("Wrong id");
        return new ProjectDTO(project);
    }

    @NotNull
    @Override
    public List<ProjectDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findByUserId(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.persist(project);
    }

    @Override
    public void merge(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.merge(project);
    }


    @Override
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.update(project);
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        projectRepository.remove(id);
        taskService.removeAllTasksInProject(id);
    }

    @Override
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        projectRepository.removeAll(userId);
        taskService.removeAll(userId);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return projectRepository.findOne(id) != null;
    }

    @Override
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByCreateDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByStartDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByEndDate(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByStatus(userId)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        return projectRepository.findByName(userId, name)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        return projectRepository.findByDescription(userId, description)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
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
