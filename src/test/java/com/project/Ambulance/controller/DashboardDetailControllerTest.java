package com.project.Ambulance.controller;

import com.project.Ambulance.model.*;
import com.project.Ambulance.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DashboardController.class)
class DashboardDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private AmbulanceService ambulanceService;
    @MockBean private BookingService bookingService;
    @MockBean private DriverService driverService;
    @MockBean private HospitalService hospitalService;
    @MockBean private MedicalStaffService medicalStaffService;
    @MockBean private ProvinceService provinceService;
    @MockBean private DistrictService districtService;
    @MockBean private WardService wardService;
    @MockBean private BrandAmbulanceService brandAmbulanceService;
    @MockBean private UploadFile uploadFile;

    @Test
    void ambulanceDetailReturnsOk() throws Exception {
        when(ambulanceService.getAmbulanceById(1)).thenReturn(new Ambulance());
        when(brandAmbulanceService.getAllBrands()).thenReturn(Collections.emptyList());
        when(hospitalService.getAllHospitals()).thenReturn(Collections.emptyList());
        when(driverService.getAllDrivers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/ambulance/1"))
                .andExpect(status().isOk());
    }

    @Test
    void brandDetailReturnsOk() throws Exception {
        when(brandAmbulanceService.getBrandById(1)).thenReturn(new BrandAmbulance());
        mockMvc.perform(get("/admin/brand-ambulance/1"))
                .andExpect(status().isOk());
    }

    @Test
    void hospitalDetailReturnsOk() throws Exception {
        when(hospitalService.getHospitalById(1)).thenReturn(new Hospital());
        mockMvc.perform(get("/admin/hospital/1"))
                .andExpect(status().isOk());
    }

    @Test
    void driverDetailReturnsOk() throws Exception {
        when(driverService.getDriverById(1)).thenReturn(new Driver());
        mockMvc.perform(get("/admin/driver/1"))
                .andExpect(status().isOk());
    }

    @Test
    void medicalDetailReturnsOk() throws Exception {
        when(medicalStaffService.getById(1)).thenReturn(new MedicalStaff());
        when(hospitalService.getAllHospitals()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/medical/1"))
                .andExpect(status().isOk());
    }

    @Test
    void bookingDetailReturnsOk() throws Exception {
        when(bookingService.getBookingById(1)).thenReturn(new Booking());
        when(ambulanceService.getAllAmbulances()).thenReturn(Collections.emptyList());
        when(driverService.getAllDrivers()).thenReturn(Collections.emptyList());
        when(medicalStaffService.getAllMedicalStaff()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/booking/1"))
                .andExpect(status().isOk());
    }

    @Test
    void provinceDetailReturnsOk() throws Exception {
        when(provinceService.getProvince(1)).thenReturn(new Province());
        mockMvc.perform(get("/admin/province/1"))
                .andExpect(status().isOk());
    }

    @Test
    void districtDetailReturnsOk() throws Exception {
        when(districtService.getDistrict(1)).thenReturn(new District());
        when(provinceService.getAllProvinceOrderByName()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/district/1"))
                .andExpect(status().isOk());
    }

    @Test
    void wardDetailReturnsOk() throws Exception {
        when(wardService.getAWard(1)).thenReturn(new Ward());
        when(districtService.getAllDistrict()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/ward/1"))
                .andExpect(status().isOk());
    }
}
