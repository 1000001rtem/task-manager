package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.api.IntegrationTest;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.RegistrationRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.rest.feign.AuthClient;
import ru.eremin.tm.rest.feign.RegistrationClient;
import ru.eremin.tm.rest.feign.UserClient;

import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 05.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationRestTest {

    @LocalServerPort
    private int port;

    @Test
    public void registrationTest() {
        @NotNull final RegistrationClient registrationClient = RegistrationClient.client(port);
        @NotNull final RegistrationRequest registrationRequest = new RegistrationRequest("registrationTest", "pass");
        registrationClient.registration(registrationRequest);

        @Nullable final String token = adminAuth();
        @NotNull final UserClient userClient = UserClient.client(port);
        @Nullable final UserDTO userDTO = userClient.findUserByLogin(token, "registrationTest");
        assertNotNull(userDTO);
        userClient.deleteUser(token, userDTO.getId());
    }

    private String adminAuth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testAdmin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
