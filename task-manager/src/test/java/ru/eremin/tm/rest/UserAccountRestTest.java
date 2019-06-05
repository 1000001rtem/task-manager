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
import ru.eremin.tm.model.dto.web.ChangePasswordDTO;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.rest.feign.AuthClient;
import ru.eremin.tm.rest.feign.UserAccountClient;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @autor av.eremin on 05.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountRestTest {

    public static final String USER_ID = "8208421e-86df-11e9-bc42-526af7764f64";

    @LocalServerPort
    private int port;

    @Test
    public void changePasswordTest() {
        @Nullable final String token = auth("pass");
        @NotNull final UserAccountClient userAccountClient = UserAccountClient.client(port);
        @NotNull final ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(USER_ID, "pass", "newPass");
        userAccountClient.changePassword(token, changePasswordDTO);

        @Nullable final String newToken = auth("newPass");
        assertNotNull(newToken);
        assertTrue(!newToken.isEmpty());
        @NotNull final ChangePasswordDTO changePasswordDTO2 = new ChangePasswordDTO(USER_ID, "newPass", "pass");
        userAccountClient.changePassword(newToken, changePasswordDTO2);
    }

    private String auth(final String password) {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testUser", password));
        return "Bearer " + responseEntity.getToken();
    }

}
