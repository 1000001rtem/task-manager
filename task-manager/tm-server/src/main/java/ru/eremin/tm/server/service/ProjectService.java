package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.config.SqlSessionConfig;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.repository.ProjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
public class ProjectService implements IProjectService {

    @NotNull
    private final SqlSessionFactory sessionFactory = SqlSessionConfig.getSessionFactory();

    @NotNull
    @Override
    public List<ProjectDTO> findAll() {
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAll()
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public ProjectDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @Nullable final Project project = projectRepository.findOne(id);
            if (project == null) {
                throw new IncorrectDataException("Wrong id");
            }
            sqlSession.commit();
            return new ProjectDTO(project);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<ProjectDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByUserId(userId)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void persist(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final Project project = getEntity(projectDTO);
            projectRepository.persist(project);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        if (projectDTO == null) throw new IncorrectDataException("Project is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final Project project = getEntity(projectDTO);
            projectRepository.update(project);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.remove(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        @Nullable Project project = null;
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            project = projectRepository.findOne(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return project != null;
    }

    @Override
    public List<ProjectDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByCreateDate(userId)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStartDate(userId)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByEndDate(userId)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findAllSortedByStatus(userId)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByName(userId, name)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProjectDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            @NotNull final List<ProjectDTO> projectDTOS = projectRepository.findByDescription(userId, description)
                    .stream()
                    .map(ProjectDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return projectDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
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
