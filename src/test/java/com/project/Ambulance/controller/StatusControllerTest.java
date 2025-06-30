package com.project.Ambulance.controller;

import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.MedicalStaffService;
import com.project.Ambulance.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmbulanceService ambulanceService;
    @MockBean
    private DriverService driverService;
    @MockBean
    private MedicalStaffService medicalStaffService;
    @MockBean
    private BookingService bookingService;

    @Test
    void updateAmbulanceStatusShouldCallServiceAndRedirect() throws Exception {
        int id = 1;
        int status = 2;

        mockMvc.perform(post("/admin/status/ambulance/{id}", id)
                        .param("status", String.valueOf(status)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/status"));

        verify(ambulanceService).updateStatus(id, status);
    }

    @Test
    void updateDriverStatusShouldCallServiceAndRedirect() throws Exception {
        int id = 3;
        int status = 1;

        mockMvc.perform(post("/admin/status/driver/{id}", id)
                        .param("status", String.valueOf(status)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/status"));

        verify(driverService).updateStatus(id, status);
    }

    @Test
    void updateBookingStatusShouldCallServiceAndRedirect() throws Exception {
        int id = 5;
        int status = 4;

        mockMvc.perform(post("/admin/status/booking/{id}", id)
                        .param("status", String.valueOf(status)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/status"));

        verify(bookingService).updateStatus(id, status);
    }
}
