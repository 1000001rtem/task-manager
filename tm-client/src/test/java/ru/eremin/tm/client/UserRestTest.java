package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.eremin.tm.client.model.dto.ChangePasswordDTO;
import ru.eremin.tm.client.model.dto.UserDTO;
import ru.eremin.tm.client.model.dto.enumerated.Role;
import ru.eremin.tm.client.task.UserClient;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 29.05.2019.
 */

public class UserRestTest {

    private static final String URL = "http://localhost:8080/api/user";
    private static final String USER_ID = "22e0196c-3b09-4b5b-909f-a541eb584706";

    @Before
    public void before(){
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testUser");
        userDTO.setHashPassword("pass");
        @NotNull final UserClient userClient = UserClient.client(URL);
        userClient.createUser(userDTO);
        assertNotNull(userClient.findUserByLogin(userDTO.getLogin()));
    }

    @Test
    public void findAllTest(){
        @NotNull final UserClient userClient = UserClient.client(URL);
        assertTrue(!userClient.findAllUsers().isEmpty());
    }

    @Test
    public void findOneTest(){
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(userClient.findUserByLogin("testUser").getId());
        assertNotNull(userDTO);
    }

    @Test
    public void changePasswordTest(){
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(userClient.findUserByLogin("testUser").getId());
        @Nullable final ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(userDTO.getId(), "pass", "pass1");
        userClient.changePassword(changePasswordDTO);
        @Nullable final UserDTO userTMP = userClient.findOneUser(userClient.findUserByLogin("testUser").getId());
        assertTrue(!userDTO.getHashPassword().equals(userTMP));
    }

    @Test
    public void updateUser(){
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(userClient.findUserByLogin("testUser").getId());
        assertFalse(userDTO.getRole().equals(Role.ADMIN));
        userDTO.setRole(Role.ADMIN);
        userClient.updateUser(userDTO);
        @Nullable final UserDTO userTMP = userClient.findOneUser(userClient.findUserByLogin("testUser").getId());
        assertEquals(userTMP.getRole(), Role.ADMIN);
    }

    @After
    public void after(){
        @NotNull final UserClient userClient = UserClient.client(URL);
        userClient.deleteUser(userClient.findUserByLogin("testUser").getId());
    }
}
