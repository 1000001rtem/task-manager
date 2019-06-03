package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.eremin.tm.client.model.dto.ChangePasswordDTO;
import ru.eremin.tm.client.model.dto.LoginRequest;
import ru.eremin.tm.client.model.dto.ResponseSoapEntity;
import ru.eremin.tm.client.model.dto.UserDTO;
import ru.eremin.tm.client.model.dto.enumerated.Role;
import ru.eremin.tm.client.task.AuthClient;
import ru.eremin.tm.client.task.UserClient;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 29.05.2019.
 */

public class UserRestTest {

    private static final String URL = "http://localhost:8080/api/user";

    @Before
    public void before(){
        @Nullable String token = auth();
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testUser");
        userDTO.setHashPassword("pass");
        @NotNull final UserClient userClient = UserClient.client(URL);
        userClient.createUser(token, userDTO);
        assertNotNull(userClient.findUserByLogin(token, userDTO.getLogin()));
    }

    @After
    public void after(){
        @Nullable String token = auth();
        @NotNull final UserClient userClient = UserClient.client(URL);
        userClient.deleteUser(token, userClient.findUserByLogin(token, "testUser").getId());
    }

    @Test
    public void findAllTest(){
        @Nullable String token = auth();
        @NotNull final UserClient userClient = UserClient.client(URL);
        assertTrue(!userClient.findAllUsers(token).isEmpty());
    }

    @Test
    public void findOneTest(){
        @Nullable String token = auth();
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(token, userClient.findUserByLogin(token, "testUser").getId());
        assertNotNull(userDTO);
    }

    @Test
    public void changePasswordTest(){
        @Nullable String token = auth();
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(token, userClient.findUserByLogin(token, "testUser").getId());
        @Nullable final ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(userDTO.getId(), "pass", "pass1");
        userClient.changePassword(token, changePasswordDTO);
        @Nullable final UserDTO userTMP = userClient.findOneUser(token, userClient.findUserByLogin(token, "testUser").getId());
        assertTrue(!userDTO.getHashPassword().equals(userTMP));
    }

    @Test
    public void updateUser(){
        @Nullable String token = auth();
        @NotNull final UserClient userClient = UserClient.client(URL);
        @Nullable final UserDTO userDTO = userClient.findOneUser(token, userClient.findUserByLogin(token, "testUser").getId());
        assertFalse(userDTO.getRole().equals(Role.ADMIN));
        userDTO.setRole(Role.ADMIN);
        userClient.updateUser(token, userDTO);
        @Nullable final UserDTO userTMP = userClient.findOneUser(token, userClient.findUserByLogin(token,"testUser").getId());
        assertEquals(userTMP.getRole(), Role.ADMIN);
    }

    private String auth(){
        @NotNull final AuthClient authClient = AuthClient.client("http://localhost:8080/api/auth");
        @Nullable final ResponseSoapEntity responseEntity = authClient.auth(new LoginRequest("admin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
