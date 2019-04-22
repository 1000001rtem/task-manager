package ru.eremin.tm.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.AuthorizationEndpointService;
import ru.eremin.tm.server.endpoint.Login;
import ru.eremin.tm.server.endpoint.LoginResponse;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
        AuthorizationEndpointService authorizationEndpoint = new AuthorizationEndpointService();
        System.out.println(authorizationEndpoint.getAuthorizationEndpointPort().login("user", "pass").getUserRole());
    }
}
