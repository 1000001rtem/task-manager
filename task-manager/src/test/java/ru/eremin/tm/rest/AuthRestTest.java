package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.api.IntegrationTest;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.RegistrationRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.rest.feign.AuthClient;

import static junit.framework.TestCase.assertTrue;

/**
 * @autor av.eremin on 05.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthRestTest {

    private static final String USER_ID = "8208421e-86df-11e9-bc42-526af7764f64";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test()
    public void negativeAccessTest() {
        @Nullable final ResponseEntity responseProjectApi = restTemplate.getForEntity("http://localhost:" + port + "/api/project", String.class);
        assertTrue(responseProjectApi.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseTaskApi = restTemplate.getForEntity("http://localhost:" + port + "/api/task", String.class);
        assertTrue(responseTaskApi.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseUserApi = restTemplate.getForEntity("http://localhost:" + port + "/api/user", String.class);
        assertTrue(responseUserApi.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseProjectSoap = restTemplate.getForEntity("http://localhost:" + port + "/api/services/endpoint/projectEndpoint", String.class);
        assertTrue(responseProjectSoap.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseTaskSoap = restTemplate.getForEntity("http://localhost:" + port + "/api/services/endpoint/taskEndpoint", String.class);
        assertTrue(responseTaskSoap.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseUserSoap = restTemplate.getForEntity("http://localhost:" + port + "/api/services/endpoint/userEndpoint", String.class);
        assertTrue(responseUserSoap.getStatusCode() == HttpStatus.UNAUTHORIZED);
    }

    @Test()
    public void accessTest() {
        @Nullable final ResponseEntity responseServices = restTemplate.getForEntity("http://localhost:" + port + "/services", String.class);
        assertTrue(responseServices.getStatusCode() == HttpStatus.OK);

        @NotNull final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("testUser");
        loginRequest.setPassword("pass");
        @Nullable final ResponseEntity responseAuthApi = restTemplate.postForEntity("http://localhost:" + port + "/api/auth", loginRequest, String.class);
        assertTrue(responseAuthApi.getStatusCode() == HttpStatus.OK);

        @Nullable final RegistrationRequest registrationRequest = new RegistrationRequest();
        @Nullable final ResponseEntity responseRegistrationApi = restTemplate.postForEntity("http://localhost:" + port + "/api/registration", registrationRequest, String.class);
        assertTrue(responseRegistrationApi.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void authTest() {
        @Nullable final String token = userAuth();
        @NotNull final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(USER_ID, headers);

        @Nullable final ResponseEntity responseProjectApi = restTemplate.exchange("http://localhost:" + port + "/api/project/findAll", HttpMethod.GET, entity, String.class);
        assertTrue(responseProjectApi.getStatusCode() != HttpStatus.UNAUTHORIZED);

        @Nullable final ResponseEntity responseTaskApi = restTemplate.exchange("http://localhost:" + port + "/api/task/findAll", HttpMethod.GET, entity, String.class);
        assertTrue(responseTaskApi.getStatusCode() != HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void adminRoleTest() {
        @Nullable final String adminToken = adminAuth();
        @NotNull final HttpHeaders adminUserHeaders = new HttpHeaders();
        adminUserHeaders.set("Authorization", adminToken);
        HttpEntity<String> adminEntity = new HttpEntity<>(adminUserHeaders);
        @Nullable final ResponseEntity responseAdminUserApi = restTemplate.exchange("http://localhost:" + port + "/api/user/findAll", HttpMethod.GET, adminEntity, String.class);
        assertTrue(responseAdminUserApi.getStatusCode() != HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void negativeAdminRoleTest() {
        @Nullable final ResponseEntity responseUserApi = restTemplate.getForEntity("http://localhost:" + port + "/api/user", String.class);
        assertTrue(responseUserApi.getStatusCode() == HttpStatus.UNAUTHORIZED);

        @Nullable final String userToken = userAuth();
        @NotNull final HttpHeaders simpleUserHeaders = new HttpHeaders();
        simpleUserHeaders.set("Authorization", userToken);
        HttpEntity<String> entity = new HttpEntity<>(simpleUserHeaders);

        @Nullable final ResponseEntity responseSimpleUserApi = restTemplate.exchange("http://localhost:" + port + "/api/user/findAll", HttpMethod.GET, entity, String.class);
        assertTrue(responseSimpleUserApi.getStatusCode() == HttpStatus.FORBIDDEN);
    }

    private String userAuth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testUser", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

    private String adminAuth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testAdmin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
