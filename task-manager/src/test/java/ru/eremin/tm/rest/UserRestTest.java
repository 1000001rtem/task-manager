package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.api.IntegrationTest;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.rest.feign.AuthClient;
import ru.eremin.tm.rest.feign.UserClient;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 05.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestTest {

    private static final String USER_ID = "f299a6aa-8769-11e9-bc42-526af7764f64";

    @LocalServerPort
    private int port;

    private String token = "";

    @Before
    public void before() {
        token = auth();
        @NotNull final UserClient userClient = UserClient.client(port);
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test");
        userDTO.setHashPassword("pass");
        userClient.createUser(token, userDTO);
    }

    @After
    public void after() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "test");
        userClient.deleteUser(token, userDTO.getId());
    }

    @Test
    public void findAllTest() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @NotNull final List<UserDTO> userDTOS = userClient.findAllUsers(token);
        assertTrue(!userDTOS.isEmpty());
    }

    @Test
    public void findOneTest() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findOneUser(token, USER_ID);
        assertNotNull(userDTO);
    }

    @Test
    public void findByLoginTest() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "test");
        assertNotNull(userDTO);
    }

    @Test
    public void updateTest() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "test");
        assertFalse(userDTO.getRole().equals(Role.ADMIN));
        userDTO.setRole(Role.ADMIN);
        userClient.updateUser(token, userDTO);
        @Nullable final UserDTO userDTOtmp = userClient.findUserByLogin(token, "test");
        assertNotNull(userDTOtmp);
        assertEquals(userDTOtmp.getRole(), Role.ADMIN);
    }

    private String auth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testAdmin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
