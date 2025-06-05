package com.on_class.technology.infrastructure.entrypoints;


import com.on_class.technology.application.config.TestConfig;
import com.on_class.technology.infrastructure.entrypoints.handler.TechnologyHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(RouterRest.class)
@Import(TestConfig.class)
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private TechnologyHandler technologyHandler;

    @Test
    void testCreateTechnology() {
        when(technologyHandler.createTechnology(any())).thenReturn(ServerResponse.ok().bodyValue("Created"));

        webTestClient.post()
                .uri("/technology")
                .exchange()
                .expectStatus().isOk();
    }
}