package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.Hospital;
import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.model.User;
import com.project.Ambulance.model.Booking;
import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.District;
import com.project.Ambulance.model.Ward;
import com.project.Ambulance.model.BrandAmbulance;
import com.project.Ambulance.util.StatusMappingUtil;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.BookingService;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.HospitalService;
import com.project.Ambulance.service.MedicalStaffService;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.DistrictService;
import com.project.Ambulance.service.WardService;
import com.project.Ambulance.service.BrandAmbulanceService;
import com.project.Ambulance.service.UploadFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private BrandAmbulanceService brandAmbulanceService;

    @Autowired
    private UploadFile uploadFile;


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("ambulanceCount", ambulanceService.countAll());
        model.addAttribute("driverCount", driverService.countAll());
        model.addAttribute("hospitalCount", hospitalService.countAll());
        model.addAttribute("bookingCount", bookingService.count());
        return "admin/dashboard";
    }

    @GetMapping("/driver/dashboard")
    public String driverDashboard(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "driver/dashboard";
    }

    @GetMapping("/driver/profile")
    public String driverProfile(Model model, HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null) {
            return "redirect:/login";
        }
        Driver driver = driverService.getDriverById(loggedIn.getIdUser());
        if (driver == null) {
            driver = new Driver();
            driver.setIdDriver(loggedIn.getIdUser());
            driver.setName(loggedIn.getNameDisplay());
            driver.setPhone(loggedIn.getPhone());
            driver.setEmail(loggedIn.getEmail());
            driver.setDateOfBirth(loggedIn.getDateOfBirth());
            driver.setSex(loggedIn.isSex());
        }
        model.addAttribute("driver", driver);
        return "driver/profile";
    }

    @PostMapping("/driver/profile")
    public String updateDriver(@ModelAttribute("driver") Driver driver, HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null) {
            return "redirect:/login";
        }
        driver.setIdDriver(loggedIn.getIdUser());
        driverService.saveDriver(driver);
        return "redirect:/driver/profile";
    }

    @GetMapping("/driver/schedule")
    public String driverSchedule(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "driver/schedule";
    }

    @GetMapping("/medical/dashboard")
    public String medicalDashboard(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "medical/dashboard";
    }

    @GetMapping("/medical/profile")
    public String medicalProfile(Model model) {
        MedicalStaff medicalStaff = medicalStaffService
                .getAvailableMedicalStaff()
                .stream()
                .findFirst()
                .orElse(null);
        model.addAttribute("medicalStaff", medicalStaff);
        return "medical/profile";
    }

    @PostMapping("/medical/profile")
    public String updateMedical(@ModelAttribute("medicalStaff") MedicalStaff medicalStaff) {
        medicalStaffService.save(medicalStaff);
        return "redirect:/medical/profile";
    }

    @GetMapping("/medical/schedule")
    public String medicalSchedule(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "medical/schedule";
    }

    @GetMapping("/admin/ambulances")
    public String manageAmbulances(Model model) {
        model.addAttribute("ambulances", ambulanceService.getAllAmbulances());
        model.addAttribute("ambulanceStatusMap", StatusMappingUtil.ambulanceStatusMap());
        return "pages/ambulance/index.ambulance";
    }

    @GetMapping("/admin/ambulance/add")
    public String addAmbulanceForm(Model model) {
        model.addAttribute("ambulanceForm", new Ambulance());
        model.addAttribute("brandAmbulances", brandAmbulanceService.getAllBrands());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "pages/ambulance/add.ambulance";
    }

    @PostMapping("/admin/ambulances")
    public String createAmbulance(@ModelAttribute("ambulanceForm") Ambulance ambulance,
                                  @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFile.uploadSingleFile(imageFile);
            ambulance.setImage(fileName);
        }
        ambulanceService.saveAmbulance(ambulance);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/{id}/delete")
    public String deleteAmbulance(@PathVariable int id) {
        ambulanceService.deleteAmbulance(id);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/{id}/edit")
    public String editAmbulanceForm(@PathVariable int id, Model model) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        model.addAttribute("ambulanceForm", ambulance);
        model.addAttribute("brandAmbulances", brandAmbulanceService.getAllBrands());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "pages/ambulance/update.ambulance";
    }

    @PostMapping("/admin/ambulance/{id}/edit")
    public String updateAmbulance(@PathVariable int id,
                                  @ModelAttribute("ambulanceForm") Ambulance form,
                                  @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        if (ambulance == null) {
            return "redirect:/admin/ambulances";
        }

        ambulance.setName(form.getName());
        ambulance.setLicensePlate(form.getLicensePlate());
        ambulance.setDescription(form.getDescription());
        ambulance.setModelYear(form.getModelYear());
        ambulance.setStatus(form.getStatus());
        ambulance.setFuelType(form.isFuelType());
        ambulance.setFuelConsumptionPer100km(form.getFuelConsumptionPer100km());
        ambulance.setNumberOfSeats(form.getNumberOfSeats());
        ambulance.setMedicalEquipment(form.isMedicalEquipment());
        ambulance.setOxygenTank(form.isOxygenTank());
        ambulance.setDefibrillator(form.isDefibrillator());
        ambulance.setPatientMonitor(form.isPatientMonitor());
        ambulance.setStretcher(form.isStretcher());
        ambulance.setInfusionSupport(form.isInfusionSupport());
        ambulance.setIncubatorSupport(form.isIncubatorSupport());
        ambulance.setGpsLocator(form.isGpsLocator());
        ambulance.setCamera360(form.isCamera360());
        ambulance.setReverseCamera(form.isReverseCamera());
        ambulance.setDashCamera(form.isDashCamera());
        ambulance.setTpms(form.isTpms());
        ambulance.setImpactSensor(form.isImpactSensor());
        ambulance.setSpeedWarning(form.isSpeedWarning());
        ambulance.setAirConditioning(form.isAirConditioning());
        ambulance.setManualTransmission(form.isManualTransmission());
        ambulance.setCurrentLocation(form.getCurrentLocation());
        ambulance.setBrandAmbulance(form.getBrandAmbulance());
        ambulance.setHospital(form.getHospital());
        ambulance.setDriver(form.getDriver());

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFile.uploadSingleFile(imageFile);
            ambulance.setImage(fileName);
        }
        ambulanceService.saveAmbulance(ambulance);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/{id}")
    public String detailAmbulance(@PathVariable int id, Model model) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        model.addAttribute("ambulance", ambulance);
        model.addAttribute("brandAmbulances", brandAmbulanceService.getAllBrands());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "pages/ambulance/detail.ambulance";
    }

    @GetMapping("/admin/hospitals")
    public String manageHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "pages/hospital/index.hospital";
    }

    @GetMapping("/admin/hospital/add")
    public String addHospitalForm(Model model) {
        model.addAttribute("hospitalForm", new Hospital());
        return "pages/hospital/add.hospital";
    }

    @PostMapping("/admin/hospitals")
    public String createHospital(@ModelAttribute("hospitalForm") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/hospital/{id}/delete")
    public String deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/hospital/{id}/edit")
    public String editHospitalForm(@PathVariable int id, Model model) {
        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("hospitalForm", hospital);
        return "pages/hospital/update.hospital";
    }

    @PostMapping("/admin/hospital/{id}/edit")
    public String updateHospital(@PathVariable int id,
                                 @ModelAttribute("hospitalForm") Hospital hospital) {
        hospital.setIdHospital(id);
        hospitalService.saveHospital(hospital);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/hospital/{id}")
    public String detailHospital(@PathVariable int id, Model model) {
        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("hospital", hospital);
        return "pages/hospital/detail.hospital";
    }

    @GetMapping("/admin/drivers")
    public String manageDrivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("driverStatusMap", StatusMappingUtil.driverStatusMap());
        return "pages/driver/index.driver";
    }

    @GetMapping("/admin/driver/add")
    public String addDriverForm(Model model) {
        model.addAttribute("driverForm", new Driver());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("driverStatusMap", StatusMappingUtil.driverStatusMap());
        return "pages/driver/add.driver";
    }

    @PostMapping("/admin/drivers")
    public String createDriver(@ModelAttribute("driverForm") Driver driver,
                               @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFile.uploadSingleFile(imageFile);
            driver.setAvatar(fileName);
        }
        driverService.saveDriver(driver);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/driver/{id}/delete")
    public String deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/driver/{id}/edit")
    public String editDriverForm(@PathVariable int id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driverForm", driver);
        return "pages/driver/update.driver";
    }

    @PostMapping("/admin/driver/{id}/edit")
    public String updateDriver(@PathVariable int id,
                               @ModelAttribute("driverForm") Driver driver) {
        driver.setIdDriver(id);
        driverService.saveDriver(driver);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/driver/{id}")
    public String detailDriver(@PathVariable int id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driver", driver);
        return "pages/driver/detail.driver";
    }

    // === Medical Staff Management ===
    @GetMapping("/admin/medicalStaffs")
    public String manageMedicalStaff(Model model) {
        model.addAttribute("medicalStaffs", medicalStaffService.getAllMedicalStaff());
        model.addAttribute("medicalStatusMap", StatusMappingUtil.medicalStatusMap());
        return "pages/medical/index.medical";
    }

    @GetMapping("/admin/medical/add")
    public String addMedicalForm(Model model) {
        model.addAttribute("medicalForm", new MedicalStaff());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("medicalStatusMap", StatusMappingUtil.medicalStatusMap());
        return "pages/medical/add.medical";
    }

    @PostMapping("/admin/medicalStaffs")
    public String createMedical(@ModelAttribute("medicalForm") MedicalStaff staff,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFile.uploadSingleFile(imageFile);
            staff.setAvatar(fileName);
        }
        medicalStaffService.save(staff);
        return "redirect:/admin/medicalStaffs";
    }

    @GetMapping("/admin/medical/{id}/delete")
    public String deleteMedical(@PathVariable int id) {
        medicalStaffService.delete(id);
        return "redirect:/admin/medicalStaffs";
    }

    @GetMapping("/admin/medical/{id}/edit")
    public String editMedicalForm(@PathVariable int id, Model model) {
        MedicalStaff staff = medicalStaffService.getById(id);
        model.addAttribute("medicalForm", staff);
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("medicalStatusMap", StatusMappingUtil.medicalStatusMap());
        return "pages/medical/update.medical";
    }

    @PostMapping("/admin/medical/{id}/edit")
    public String updateMedical(@PathVariable int id,
                                @ModelAttribute("medicalForm") MedicalStaff staff,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        staff.setIdMedicalStaff(id);
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFile.uploadSingleFile(imageFile);
            staff.setAvatar(fileName);
        }
        medicalStaffService.save(staff);
        return "redirect:/admin/medicalStaffs";
    }

    @GetMapping("/admin/medical/{id}")
    public String detailMedical(@PathVariable int id, Model model) {
        MedicalStaff staff = medicalStaffService.getById(id);
        model.addAttribute("medicalStaff", staff);
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("medicalStatusMap", StatusMappingUtil.medicalStatusMap());
        return "pages/medical/detail.medical";
    }

    @GetMapping("/admin/bookings")
    public String bookingHistory(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("bookingStatusMap", StatusMappingUtil.bookingStatusMap());
        return "pages/booking/index.booking";
    }

    @GetMapping("/admin/booking/add")
    public String addBookingForm(Model model) {
        model.addAttribute("bookingForm", new Booking());
        model.addAttribute("ambulances", ambulanceService.getAllAmbulances());
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("medicalStaff", medicalStaffService.getAllMedicalStaff());
        return "pages/booking/add.booking";
    }

    @PostMapping("/admin/bookings")
    public String createBooking(@ModelAttribute("bookingForm") Booking booking,
                                @RequestParam int driverId,
                                @RequestParam(value = "medicalStaffIds", required = false) List<Integer> medicalStaffIds,
                                HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn != null) {
            booking.setUser(loggedIn);
        }
        booking.setRequestTime(new Date());
        Driver driver = driverService.getDriverById(driverId);
        booking.setDriver(driver);
        if (medicalStaffIds != null && !medicalStaffIds.isEmpty()) {
            List<MedicalStaff> staff = medicalStaffService.getByIds(medicalStaffIds);
            booking.setMedicalStaffList(staff);
        }
        bookingService.saveBooking(booking);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/admin/booking/{id}")
    public String detailBooking(@PathVariable int id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("ambulances", ambulanceService.getAllAmbulances());
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("medicalStaff", medicalStaffService.getAllMedicalStaff());
        model.addAttribute("bookingStatusMap", StatusMappingUtil.bookingStatusMap());
        return "pages/booking/detail.booking";
    }

    // === Province Management ===
    @GetMapping("/admin/provinces")
    public String manageProvinces(Model model) {
        model.addAttribute("provinces", provinceService.getAllProvinceOrderByName());
        return "pages/province/index.province";
    }

    @GetMapping("/admin/province/add")
    public String addProvinceForm(Model model) {
        model.addAttribute("provinceForm", new Province());
        return "pages/province/add.province";
    }

    @PostMapping("/admin/provinces")
    public String createProvince(@ModelAttribute("provinceForm") Province province) {
        provinceService.saveProvince(province);
        return "redirect:/admin/provinces";
    }

    @GetMapping("/admin/province/{id}/delete")
    public String deleteProvince(@PathVariable int id) {
        provinceService.deleteProvince(id);
        return "redirect:/admin/provinces";
    }

    @GetMapping("/admin/province/{id}/edit")
    public String editProvinceForm(@PathVariable int id, Model model) {
        Province province = provinceService.getProvince(id);
        model.addAttribute("provinceForm", province);
        return "pages/province/update.province";
    }

    @PostMapping("/admin/province/{id}/edit")
    public String updateProvince(@PathVariable int id,
                                 @ModelAttribute("provinceForm") Province province) {
        province.setIdProvince(id);
        provinceService.saveProvince(province);
        return "redirect:/admin/provinces";
    }

    @GetMapping("/admin/province/{id}")
    public String detailProvince(@PathVariable int id, Model model) {
        Province province = provinceService.getProvince(id);
        model.addAttribute("province", province);
        return "pages/province/detail.province";
    }

    // === District Management ===
    @GetMapping("/admin/districts")
    public String manageDistricts(Model model) {
        model.addAttribute("districts", districtService.getAllDistrict());
        return "pages/district/index.district";
    }

    @GetMapping("/admin/district/add")
    public String addDistrictForm(Model model) {
        model.addAttribute("districtForm", new District());
        model.addAttribute("provinces", provinceService.getAllProvinceOrderByName());
        return "pages/district/add.district";
    }

    @PostMapping("/admin/districts")
    public String createDistrict(@ModelAttribute("districtForm") District district) {
        districtService.saveDistrict(district);
        return "redirect:/admin/districts";
    }

    @GetMapping("/admin/district/{id}/delete")
    public String deleteDistrict(@PathVariable int id) {
        districtService.deleteDistrict(id);
        return "redirect:/admin/districts";
    }

    @GetMapping("/admin/district/{id}/edit")
    public String editDistrictForm(@PathVariable int id, Model model) {
        District district = districtService.getDistrict(id);
        model.addAttribute("districtForm", district);
        model.addAttribute("provinces", provinceService.getAllProvinceOrderByName());
        return "pages/district/update.district";
    }

    @PostMapping("/admin/district/{id}/edit")
    public String updateDistrict(@PathVariable int id,
                                 @ModelAttribute("districtForm") District district) {
        district.setIdDistrict(id);
        districtService.saveDistrict(district);
        return "redirect:/admin/districts";
    }

    @GetMapping("/admin/district/{id}")
    public String detailDistrict(@PathVariable int id, Model model) {
        District district = districtService.getDistrict(id);
        model.addAttribute("district", district);
        model.addAttribute("provinces", provinceService.getAllProvinceOrderByName());
        return "pages/district/detail.district";
    }

    // === Ward Management ===
    @GetMapping("/admin/wards")
    public String manageWards(Model model) {
        model.addAttribute("wards", wardService.getAllWard());
        return "pages/ward/index.ward";
    }

    @GetMapping("/admin/ward/add")
    public String addWardForm(Model model) {
        model.addAttribute("wardForm", new Ward());
        model.addAttribute("districts", districtService.getAllDistrict());
        return "pages/ward/add.ward";
    }

    @PostMapping("/admin/wards")
    public String createWard(@ModelAttribute("wardForm") Ward ward) {
        wardService.saveWard(ward);
        return "redirect:/admin/wards";
    }

    @GetMapping("/admin/ward/{id}/delete")
    public String deleteWard(@PathVariable int id) {
        wardService.deleteWard(id);
        return "redirect:/admin/wards";
    }

    @GetMapping("/admin/ward/{id}/edit")
    public String editWardForm(@PathVariable int id, Model model) {
        Ward ward = wardService.getAWard(id);
        model.addAttribute("wardForm", ward);
        model.addAttribute("districts", districtService.getAllDistrict());
        return "pages/ward/update.ward";
    }

    @PostMapping("/admin/ward/{id}/edit")
    public String updateWard(@PathVariable int id,
                             @ModelAttribute("wardForm") Ward ward) {
        ward.setIdWard(id);
        wardService.saveWard(ward);
        return "redirect:/admin/wards";
    }

    @GetMapping("/admin/ward/{id}")
    public String detailWard(@PathVariable int id, Model model) {
        Ward ward = wardService.getAWard(id);
        model.addAttribute("ward", ward);
        model.addAttribute("districts", districtService.getAllDistrict());
        return "pages/ward/detail.ward";
    }

    // === Brand Ambulance Management ===
    @GetMapping("/admin/brand-ambulances")
    public String manageBrands(Model model) {
        model.addAttribute("brands", brandAmbulanceService.getAllBrands());
        return "pages/brand/index.brand";
    }

    @GetMapping("/admin/brand-ambulance/add")
    public String addBrandForm(Model model) {
        model.addAttribute("brandForm", new BrandAmbulance());
        return "pages/brand/add.brand";
    }

    @PostMapping("/admin/brand-ambulances")
    public String createBrand(@ModelAttribute("brandForm") BrandAmbulance brand) {
        brandAmbulanceService.saveBrand(brand);
        return "redirect:/admin/brand-ambulances";
    }

    @GetMapping("/admin/brand-ambulance/{id}/edit")
    public String editBrandForm(@PathVariable int id, Model model) {
        BrandAmbulance brand = brandAmbulanceService.getBrandById(id);
        model.addAttribute("brandForm", brand);
        return "pages/brand/update.brand";
    }

    @PostMapping("/admin/brand-ambulance/{id}/edit")
    public String updateBrand(@PathVariable int id,
                              @ModelAttribute("brandForm") BrandAmbulance brand) {
        brand.setIdBrand(id);
        brandAmbulanceService.saveBrand(brand);
        return "redirect:/admin/brand-ambulances";
    }

    @GetMapping("/admin/brand-ambulance/{id}")
    public String detailBrand(@PathVariable int id, Model model) {
        BrandAmbulance brand = brandAmbulanceService.getBrandById(id);
        model.addAttribute("brand", brand);
        return "pages/brand/detail.brand";
    }

    @GetMapping("/admin/brand-ambulance/{id}/delete")
    public String deleteBrand(@PathVariable int id) {
        brandAmbulanceService.deleteBrand(id);
        return "redirect:/admin/brand-ambulances";
    }
}
