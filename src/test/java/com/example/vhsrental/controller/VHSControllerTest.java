package com.example.vhsrental.controller;

import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.service.VHSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VHSControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VHSService vhsService;

    @InjectMocks
    private VHSController vhsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vhsController).build();
    }

    @Test
    void getAllVHS() throws Exception {
        when(vhsService.getAllVHS()).thenReturn(Collections.singletonList(new VHS()));

        mockMvc.perform(get("/api/vhs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(vhsService, times(1)).getAllVHS();
    }

    @Test
    void getVHSById() throws Exception {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsService.getVHSById(anyLong())).thenReturn(vhs);

        mockMvc.perform(get("/api/vhs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("The Matrix"))
                .andExpect(jsonPath("$.genre").value("Sci-Fi"))
                .andExpect(jsonPath("$.releaseYear").value(1999));

        verify(vhsService, times(1)).getVHSById(anyLong());
    }

    @Test
    void createVHS() throws Exception {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsService.createVHS(any(VHS.class))).thenReturn(vhs);

        mockMvc.perform(post("/api/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"The Matrix\",\"genre\":\"Sci-Fi\",\"releaseYear\":1999}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("The Matrix"))
                .andExpect(jsonPath("$.genre").value("Sci-Fi"))
                .andExpect(jsonPath("$.releaseYear").value(1999));

        verify(vhsService, times(1)).createVHS(any(VHS.class));
    }

    @Test
    void updateVHS() throws Exception {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsService.updateVHS(anyLong(), any(VHS.class))).thenReturn(vhs);

        mockMvc.perform(put("/api/vhs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"The Matrix\",\"genre\":\"Sci-Fi\",\"releaseYear\":1999}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("The Matrix"))
                .andExpect(jsonPath("$.genre").value("Sci-Fi"))
                .andExpect(jsonPath("$.releaseYear").value(1999));

        verify(vhsService, times(1)).updateVHS(anyLong(), any(VHS.class));
    }

    @Test
    void deleteVHS() throws Exception {
        doNothing().when(vhsService).deleteVHS(anyLong());

        mockMvc.perform(delete("/api/vhs/1"))
                .andExpect(status().isNoContent());

        verify(vhsService, times(1)).deleteVHS(anyLong());
    }
}
