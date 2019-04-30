package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectRepository;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.IUserRepository;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.repository.ProjectRepository;
import ru.eremin.tm.server.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Nullable
    private EntityManagerFactory entityManagerFactory;

    public ProjectService(@Nullable final EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory == null) return;
        this.entityManagerFactory = entityManagerFactory;
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll() {
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            final List<ProjectDTO> projectDTOS = projectRepository.findAll()
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOne(id);
            if (project == null) throw new IncorrectDataException("Wrong id");
            em.getTransaction().commit();
            return new ProjectDTO(project);
        } catch (IncorrectDataException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<ProjectDTO> findByUserId(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByUserId(user)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Project project = getEntity(projectDTO, em);
            projectRepository.persist(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Project project = getEntity(projectDTO, em);
            projectRepository.update(project);
            em.getTransaction().commit();
        } catch (IncorrectDataException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            projectRepository.remove(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAll(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            projectRepository.removeAll(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOne(id);
            em.getTransaction().commit();
            return project != null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByCreateDate(user)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStartDate(user)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByEndDate(user)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final UserDTO userDTO) throws AccessForbiddenException {
        if (userDTO == null) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStatus(user)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findByName(@Nullable final UserDTO userDTO, @Nullable final String name) throws AccessForbiddenException {
        if (userDTO == null || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByName(user, name)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findByDescription(@Nullable final UserDTO userDTO, @Nullable final String description) throws AccessForbiddenException {
        if (userDTO == null || description == null || description.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userDTO, em);
            if(user == null) throw new AccessForbiddenException();
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByDescription(user, description)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return projectDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Project getEntity(@NotNull final ProjectDTO projectDTO, @NotNull final EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
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

    private User getUser(final UserDTO userDTO, final EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        return userRepository.findOne(userDTO.getId());
    }

}
