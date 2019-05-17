package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.api.IProjectRepository;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
@Service(ProjectService.NAME)
public class ProjectService implements IProjectService {

    public static final String NAME = "projectService";

    @NotNull
    @Autowired
    private IProjectRepository projectRepository;

    @NotNull
    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public @NotNull List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Project project = projectRepository.findOne(id);
        if (project == null) throw new IncorrectDataException("Wrong id");
        return new ProjectDTO(project);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findByUserId(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.persist(project);
    }

    @Override
    @Transactional
    public void merge(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.merge(project);
    }


    @Override
    @Transactional
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.update(project);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        projectRepository.remove(id);
    }

    @Override
    @Transactional
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return;
        projectRepository.removeAll(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return projectRepository.findOne(id) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByCreateDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByStartDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByEndDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findAllSortedByStatus(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findByName(user, name)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return projectRepository.findByDescription(user, description)
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
            project.setUser(userRepository.findOne(projectDTO.getUserId()));
        }
        project.setStatus(projectDTO.getStatus());
        project.setCreateDate(projectDTO.getCreateDate());
        return project;
    }

}
