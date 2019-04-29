package ru.eremin.tm.server.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.utils.FieldConst;

import java.util.List;

/**
 * @autor av.eremin on 29.04.2019.
 */

public interface SessionRepository {

    @Select("SELECT * FROM `session_table`")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "userRole", column = FieldConst.USER_ROLE)})
    List<Session> findAll();

    @Select("SELECT * FROM `session_table` WHERE `id` = #{id};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "userRole", column = FieldConst.USER_ROLE)})
    Session findOne(@NotNull final String id);

    @Select("SELECT * FROM `session_table` WHERE `user_id` = @{userId};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "userId", column = FieldConst.USER_ID),
            @Result(property = "userRole", column = FieldConst.USER_ROLE)})
    Session findByUserId(@NotNull final String userId);

    @Insert("INSERT INTO `session_table`" + "(" +
            FieldConst.ID + ", " +
            FieldConst.CREATE_DATE + ", " +
            FieldConst.USER_ID + ", " +
            FieldConst.USER_ROLE + ", " +
            FieldConst.SIGN + ") " +
            "VALUES (#{id},#{createDate},#{userId},#{userRole},#{sign});")
    void persist(@NotNull final Session session);

    @Update("UPDATE `session_table` SET " +
            FieldConst.USER_ID + "= #{userId}, " +
            FieldConst.USER_ROLE + "= #{userRole}, " +
            FieldConst.SIGN + "= #{sign} " +
            "WHERE `id` = #{id};")
    void update(@NotNull final Session session);

    @Delete("DELETE FROM `session_table` WHERE id = #{id}")
    void remove(@NotNull final String id);

    @Delete("DELETE FROM `session_table`")
    void removeAll();
}
