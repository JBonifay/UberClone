package com.joffrey.uberclone.integration.adapters.primary;

import com.joffrey.uberclone.integration.adapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class MapControllerIT extends AbstractIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @Test
    void should_return_map() throws Exception {
        mvc.perform(get("/api/map")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                {
                                  "blocks": [
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 0,
                                      "y": 0
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 1,
                                      "y": 0
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 2,
                                      "y": 0
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 0,
                                      "y": 1
                                    },
                                    {
                                      "blockType": "BUILDING",
                                      "color": "#d77a61",
                                      "x": 1,
                                      "y": 1
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 2,
                                      "y": 1
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 0,
                                      "y": 2
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 1,
                                      "y": 2
                                    },
                                    {
                                      "blockType": "ROAD",
                                      "color": "#f5f5f5",
                                      "x": 2,
                                      "y": 2
                                    }
                                  ]
                                }
                                                        """));
    }

}