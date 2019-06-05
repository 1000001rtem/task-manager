package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.rest.feign.AuthClient;
import ru.eremin.tm.rest.feign.UserClient;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 05.06.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestTest {

    private static final String USER_ID = "6706e691-2f78-45ad-b021-3730c48959f0";

    @LocalServerPort
    private int port;

    private String token = "";

    @Before
    public void before() {
        token = auth();
        @NotNull final UserClient userClient = UserClient.client(port);
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testUser");
        userDTO.setHashPassword("pass");
        userClient.createUser(token, userDTO);
    }

    @After
    public void after() {
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "testUser");
        userClient.deleteUser(token, userDTO.getId());
    }

    @Test
    public void findAllTest(){
        @NotNull final UserClient userClient = UserClient.client(port);
        @NotNull final List<UserDTO> userDTOS = userClient.findAllUsers(token);
        assertTrue(!userDTOS.isEmpty());
    }

    @Test
    public void findOneTest(){
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findOneUser(token, USER_ID);
        assertNotNull(userDTO);
    }

    @Test
    public void findByLoginTest(){
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "testUser");
        assertNotNull(userDTO);
    }

    @Test
    public void updateTest(){
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "testUser");
        assertFalse(userDTO.getRole().equals(Role.ADMIN));
        userDTO.setRole(Role.ADMIN);
        userClient.updateUser(token, userDTO);
        @Nullable final UserDTO userDTOtmp = userClient.findUserByLogin(token, "testUser");
        assertNotNull(userDTOtmp);
        assertEquals(userDTOtmp.getRole(), Role.ADMIN);
    }

    private String auth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("admin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
