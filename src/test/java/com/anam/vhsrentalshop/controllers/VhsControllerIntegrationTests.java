package com.anam.vhsrentalshop.controllers;

import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.services.VhsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VhsControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VhsService vhsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatGetAllVhsReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAllVhsReturnsVhsList() throws Exception {

        VhsEntity testVhsEntityA = new VhsEntity(1L, "The Matrix", "Sci-Fi", 1999);
        VhsEntity testVhsEntityB = new VhsEntity(2L, "The Godfather", "Crime", 1972);
        vhsService.save(testVhsEntityA);
        vhsService.save(testVhsEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(testVhsEntityA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(testVhsEntityA.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].genre").value(testVhsEntityA.getGenre())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(testVhsEntityA.getReleaseYear())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(testVhsEntityB.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(testVhsEntityB.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].genre").value(testVhsEntityB.getGenre())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].releaseYear").value(testVhsEntityB.getReleaseYear())
        );
    }
}

