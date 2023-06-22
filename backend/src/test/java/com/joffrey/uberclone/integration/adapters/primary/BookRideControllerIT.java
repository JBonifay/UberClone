package com.joffrey.uberclone.integration.adapters.primary;

import com.joffrey.uberclone.integration.adapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class BookRideControllerIT extends AbstractIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnSimulationMap() {
        webTestClient
                .get()
                .uri("/api/map")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .json("""
                        {
                          "blocks": [
                            [
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              },
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              },
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              }
                            ],
                            [
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              },
                              {
                                "name": "BUILDING",
                                "color": "#d77a61"
                              },
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              }
                            ],
                            [
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              },
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              },
                              {
                                "name": "ROAD",
                                "color": "#000000"
                              }
                            ]
                          ]
                        }""");
    }
}