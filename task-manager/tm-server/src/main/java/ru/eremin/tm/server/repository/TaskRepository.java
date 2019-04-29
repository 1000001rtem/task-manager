package ru.eremin.tm.server.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.utils.FieldConst;

import java.util.List;

/**
 * @autor av.eremin on 29.04.2019.
 */

public interface TaskRepository {

    @Select("SELECT * FROM `task_table` WHERE `project_id` = #{projectId};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findByProjectId(@NotNull final String projectId);

    @Select("SELECT * FROM `task_table`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findAll();

    @Select("SELECT * FROM `task_table` WHERE `id` = #{id};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    Task findOne(@NotNull final String id);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} ORDER BY `create_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findAllSortedByCreateDate(@NotNull final String userId);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} ORDER BY `start_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findAllSortedByStartDate(@NotNull final String userId);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} ORDER BY `end_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findAllSortedByEndDate(@NotNull final String userId);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} ORDER BY `task_status`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findAllSortedByStatus(@NotNull final String userId);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} and `task_name` LIKE #{name};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findByName(@NotNull final String userId, @NotNull final String name);

    @Select("SELECT * FROM `task_table` WHERE `user_id` = #{userId} and `task_description` LIKE #{description};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.TASK_NAME),
            @Result(property = "description", column = FieldConst.TASK_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.TASK_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "projectId", column = FieldConst.PROJECT_ID)})
    List<Task> findByDescription(@NotNull final String userId, @NotNull final String description);

    @Insert("INSERT INTO `task_table`" + "(" +
            FieldConst.ID + ", " +
            FieldConst.CREATE_DATE + ", " +
            FieldConst.TASK_NAME + ", " +
            FieldConst.TASK_DESC + ", " +
            FieldConst.START_DATE + ", " +
            FieldConst.END_DATE + ", " +
            FieldConst.TASK_STATUS + ", " +
            FieldConst.USER_ID + ", " +
            FieldConst.PROJECT_ID + ") " +
            "VALUES (#{id},#{createDate},#{name},#{description},#{startDate},#{endDate},#{status},#{userId},#{projectId});")
    void persist(@NotNull final Task task);

    @Update("UPDATE `task_table` SET " +
            FieldConst.TASK_NAME + "= #{name}, " +
            FieldConst.TASK_DESC + "= #{description}, " +
            FieldConst.START_DATE + "= #{startDate}, " +
            FieldConst.END_DATE + "= #{endDate}, " +
            FieldConst.TASK_STATUS + "= #{status}, " +
            FieldConst.USER_ID + "= #{userId}, " +
            FieldConst.PROJECT_ID + "= #{projectId} " +
            "WHERE `id` = #{id};")
    void update(@NotNull final Task task);

    @Delete("DELETE FROM `task_table` WHERE id = #{id}")
    void remove(@NotNull final String id);

    @Delete("DELETE FROM `task_table` WHERE project_id = #{projectId}")
    void removeAllTasksInProject(@Nullable final String projectId);

    @Delete("DELETE FROM `task_table` WHERE user_id = #{userId}")
    void removeAll(@NotNull final String userId);

}
