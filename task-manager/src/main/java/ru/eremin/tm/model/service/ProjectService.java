package ru.eremin.tm.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.repository.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public enum ProjectService implements IProjectService {

    INSTANCE;

    private final ProjectRepository projectRepository;

    private final TaskService taskService;

    ProjectService() {
        this.projectRepository = ProjectRepository.INSTANCE;
        this.taskService = TaskService.INSTANCE;
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Nullable
    public ProjectDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Project project = projectRepository.findById(id);
        if (project == null) return null;
        return new ProjectDTO(project);
    }

    @Override
    public void insert(@Nullable final ProjectDTO projectDTO) {
        if (projectDTO == null) return;
        final Project project = getEntity(projectDTO);
        projectRepository.insert(project);
    }

    @Override
    public void update(@Nullable final ProjectDTO projectDTO) {
        if (projectDTO == null) return;
        if (projectRepository.findById(projectDTO.getId()) == null) return;
        final Project project = getEntity(projectDTO);
        projectRepository.update(project);
    }

    @Override
    public boolean delete(@Nullable final String id) {
        if (id == null || id.isEmpty() || !isExist(id)) return false;
        projectRepository.delete(id);
        taskService.deleteAllTasksInProject(id);
        return true;
    }

    @Override
    public void deleteAll() {
        projectRepository.deleteAll();
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return projectRepository.findById(id) != null;
    }

    @Override
    @NotNull
    public Project getEntity(@NotNull final ProjectDTO projectDTO) {
        final Project project = new Project();
        project.setId(projectDTO.getId());
        if (projectDTO.getName() != null && !projectDTO.getName().isEmpty()) project.setName(projectDTO.getName());
        if (projectDTO.getDescription() != null && !projectDTO.getDescription().isEmpty()) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStartDate() != null) project.setStartDate(projectDTO.getStartDate());
        if (projectDTO.getEndDate() != null) project.setEndDate(projectDTO.getEndDate());
        return project;
    }

}
