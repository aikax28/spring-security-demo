package com.aikax28.springframework.security.demo.cotroller;

import com.aikax28.springframework.security.demo.SpringSecurityDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 *
 * @author aikax28
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringSecurityDemoApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
@WebFluxTest
public class IndexControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser
    public void ok() {
        webTestClient
                .get()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> Assert.assertEquals("ok", new String(response.getResponseBody())));
    }

    @Test
    public void okForUnauthorized() {
        webTestClient
                .get()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody().isEmpty();
    }
}
