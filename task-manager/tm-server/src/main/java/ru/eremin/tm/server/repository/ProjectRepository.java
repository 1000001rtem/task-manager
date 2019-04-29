package ru.eremin.tm.server.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.utils.FieldConst;

import java.util.List;

/**
 * @autor av.eremin on 29.04.2019.
 */

public interface ProjectRepository {

    @Select("SELECT * FROM `project_table`;")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findAll();

    @Select("SELECT * FROM `project_table` WHERE `id` = #{id};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    Project findOne(@NotNull final String id);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId}")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId} ORDER BY `create_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findAllSortedByCreateDate(@NotNull final String userId);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId} ORDER BY `start_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findAllSortedByStartDate(@NotNull final String userId);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId} ORDER BY `end_date`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findAllSortedByEndDate(@NotNull final String userId);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId} ORDER BY `project_status`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findAllSortedByStatus(@NotNull final String userId);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{userId} and `project_name` LIKE #{name};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findByName(@NotNull final String userId, @NotNull final String name);

    @Select("SELECT * FROM `project_table` WHERE `user_id` = #{id} and `project_description` LIKE #{description};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "name", column = FieldConst.PROJECT_NAME),
            @Result(property = "description", column = FieldConst.PROJECT_DESC),
            @Result(property = "startDate", column = FieldConst.START_DATE),
            @Result(property = "endDate", column = FieldConst.END_DATE),
            @Result(property = "status", column = FieldConst.PROJECT_STATUS),
            @Result(property = "userId", column = FieldConst.USER_ID)})
    List<Project> findByDescription(@NotNull final String userId, @NotNull final String description);

    @Insert("INSERT INTO `project_table`" + "(" +
            FieldConst.ID + ", " +
            FieldConst.CREATE_DATE + ", " +
            FieldConst.PROJECT_NAME + ", " +
            FieldConst.PROJECT_DESC + ", " +
            FieldConst.START_DATE + ", " +
            FieldConst.END_DATE + ", " +
            FieldConst.PROJECT_STATUS + ", " +
            FieldConst.USER_ID + ") " +
            "VALUES (#{id}, #{createDate}, #{name}, #{description}, #{startDate}, #{endDate}, #{status}, #{userId});")
    void persist(@NotNull final Project project);

    @Update("UPDATE `project_table` SET " +
            FieldConst.PROJECT_NAME + "= #{name}, " +
            FieldConst.PROJECT_DESC + "= #{description}, " +
            FieldConst.START_DATE + "= #{startDate}, " +
            FieldConst.END_DATE + "= #{endDate}, " +
            FieldConst.PROJECT_STATUS + "= #{status}, " +
            FieldConst.USER_ID + "= #{userId} " +
            "WHERE `id` = #{id};")
    void update(@NotNull final Project project);

    @Delete("DELETE FROM `project_table` WHERE id = #{id}")
    void remove(@NotNull final String id);

    @Delete("DELETE FROM `project_table` WHERE user_id = #{userId}")
    void removeAll(@NotNull final String userId);

}
