package ru.eremin.tm.server.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.utils.FieldConst;

import java.util.List;

/**
 * @autor av.eremin on 29.04.2019.
 */

public interface UserRepository {

    @Select("SELECT * FROM `user_table` WHERE `login` = #{login};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "hashPassword", column = FieldConst.HASH_PASSWORD),
            @Result(property = "role", column = FieldConst.USER_ROLE)})
    User findByLogin(@NotNull final String login);

    @Select("SELECT * FROM `user_table`;")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "hashPassword", column = FieldConst.HASH_PASSWORD),
            @Result(property = "role", column = FieldConst.USER_ROLE)})
    List<User> findAll();

    @Select("SELECT * FROM `user_table` WHERE `id` = #{id};")
    @Results({@Result(property = "createDate", column = FieldConst.CREATE_DATE),
            @Result(property = "hashPassword", column = FieldConst.HASH_PASSWORD),
            @Result(property = "role", column = FieldConst.USER_ROLE)})
    User findOne(@NotNull final String id);

    @Insert("INSERT INTO `user_table`" + "(" +
            FieldConst.ID + ", " +
            FieldConst.CREATE_DATE + ", " +
            FieldConst.LOGIN + ", " +
            FieldConst.HASH_PASSWORD + ", " +
            FieldConst.USER_ROLE + ") " +
            "VALUES (#{id},#{createDate},#{login},#{hashPassword},#{role});")
    void persist(@NotNull final User user);

    @Update("UPDATE `user_table` SET " +
            FieldConst.LOGIN + "= #{login}, " +
            FieldConst.HASH_PASSWORD + "= #{hashPassword}, " +
            FieldConst.USER_ROLE + "= #{role} " +
            "WHERE `id` = #{id};")
    void update(@NotNull final User user);

    @Delete("DELETE FROM `user_table` WHERE id = #{id}")
    void remove(@NotNull final String id);

    @Delete("DELETE FROM `user_table`")
    void removeAll();

}
