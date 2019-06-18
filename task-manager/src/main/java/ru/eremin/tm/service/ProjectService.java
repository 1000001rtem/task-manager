package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.repository.ProjectRepository;
import ru.eremin.tm.repository.UserRepository;

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
    private ProjectRepository projectRepository;

    @NotNull
    @Autowired
    private UserRepository userRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Project project = projectRepository.findById(id).orElseThrow(() -> new IncorrectDataException("Wrong id"));
        return new ProjectDTO(project);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findByUser(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findByUserId(@Nullable final String userId, @Nullable final Pageable pageable) {
        return projectRepository.findByUser(getUser(userId), pageable)
                .map(ProjectDTO::new);
    }

    @Override
    @Transactional
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        if (!isExist(projectDTO.getId())) throw new IncorrectDataException("Project is not exist");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void merge(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final Project project = getEntity(projectDTO);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Project project = projectRepository.findById(id).orElseThrow(() -> new IncorrectDataException("Wrong id"));
        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return;
        @NotNull final List<Project> projects = projectRepository.findByUser(user);
        if (projects.isEmpty()) return;
        projects.forEach(projectRepository::delete);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @Nullable final Project project = projectRepository.findById(id).orElse(null);
        return project != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findAllSortedByCreateDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findAllSortedByStartDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findAllSortedByEndDate(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findAllSortedByStatus(user)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
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
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        return projectRepository.findByDescription(user, description)
                .stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public Project getEntity(@NotNull final ProjectDTO projectDTO) {
        @NotNull final Project project = projectRepository.findById(projectDTO.getId()).orElse(new Project());
        @Nullable final User user = getUser(projectDTO.getUserId());
        project.setId(projectDTO.getId());
        if (projectDTO.getName() != null && !projectDTO.getName().isEmpty()) project.setName(projectDTO.getName());
        if (projectDTO.getDescription() != null && !projectDTO.getDescription().isEmpty()) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStartDate() != null) project.setStartDate(projectDTO.getStartDate());
        if (projectDTO.getEndDate() != null) project.setEndDate(projectDTO.getEndDate());
        if (user != null) project.setUser(user);
        project.setStatus(projectDTO.getStatus());
        project.setCreateDate(projectDTO.getCreateDate());
        return project;
    }

    @Nullable
    @Transactional(readOnly = true)
    private User getUser(@NotNull final String userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
