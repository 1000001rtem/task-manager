package ru.eremin.tm.client.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.client.api.IntegrationTest;
import ru.eremin.tm.client.config.AppClientConfiguration;
import ru.eremin.tm.client.util.PasswordHashUtil;
import ru.eremin.tm.server.endpoint.*;

import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 06.05.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClientConfiguration.class)
public class UserCommandsTest {

    @Autowired
    private UserEndpoint userEndpoint;

    private SessionDTO sessionDTO;

    @Before
    public void before() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        sessionDTO = auth(null, null);
    }

    @Test
    @Category(IntegrationTest.class)
    public void authTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        assertNotNull(sessionDTO);
        logout(sessionDTO);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        logout(sessionDTO);
        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLogin");
        userDTO.setHashPassword(PasswordHashUtil.md5("pass"));
        userDTO.setRole(Role.USER);
        userEndpoint.persistUser(userDTO);
        final SessionDTO sessionDTO1 = auth("testLogin", "pass");
        assertNotNull(sessionDTO1);
        userEndpoint.removeUser(sessionDTO1, sessionDTO1.getUserId());
        logout(sessionDTO1);
    }

    @Test
    @Category(IntegrationTest.class)
    public void changePasswordTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        userEndpoint.changeUserPassword(sessionDTO, "test", "test1");
        logout(sessionDTO);
        final SessionDTO session2 = auth(null, "test1");
        assertNotNull(session2);
        userEndpoint.changeUserPassword(session2, "test1", "test");
        logout(session2);
    }

    private SessionDTO auth(String login, String password) throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        if (login == null || login.isEmpty()) login = "test";
        if (password == null || password.isEmpty()) password = "test";
        final SessionDTO session = authorizationEndpoint.login(login, password);
        return session;
    }

    private void logout(final SessionDTO sessionDTO) throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        authorizationEndpoint.logout(sessionDTO);
    }

}
