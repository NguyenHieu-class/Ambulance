-- Seed data for Ambulance Management project
-- Roles
INSERT INTO roles (id_role, name, description) VALUES
  (1, 'ADMIN', 'System administrator'),
  (2, 'STAFF', 'Management staff'),
  (3, 'DRIVER', 'Driver account'),
  (4, 'MEDICAL', 'Medical staff account');

-- Provinces
INSERT INTO province (id_province, name_province, zip_code, img_province, create_date, update_date) VALUES
  (1, 'Sample Province', 10000, 'province.png', NOW(), NOW());

INSERT INTO province (id_province, name_province, zip_code, img_province, create_date, update_date) VALUES
  (2, 'Second Province', 20000, 'province2.png', NOW(), NOW());

-- Districts
INSERT INTO district (id_district, name_district, create_date, update_date, id_province) VALUES
  (1, 'Sample District', NOW(), NOW(), 1);

INSERT INTO district (id_district, name_district, create_date, update_date, id_province) VALUES
  (2, 'Second District', NOW(), NOW(), 2);

-- Wards
INSERT INTO ward (id_ward, name_ward, create_date, update_date, id_district) VALUES
  (1, 'Sample Ward', NOW(), NOW(), 1);

INSERT INTO ward (id_ward, name_ward, create_date, update_date, id_district) VALUES
  (2, 'Second Ward', NOW(), NOW(), 2);

-- Brand of ambulances
INSERT INTO brand_ambulance (id_brand, name_brand, create_date, update_date) VALUES
  (1, 'SampleBrand', NOW(), NOW());

-- Hospitals
INSERT INTO hospitals (id_hospital, name, address_detail, phone, email, director_name, create_date, update_date, province_id, district_id, ward_id)
VALUES
  (1, 'Central Hospital', '123 Main St', '0123456789', 'hospital@example.com', 'Dr. House', NOW(), NOW(), 1, 1, 1);

-- Drivers
INSERT INTO drivers (id_driver, name, phone, email, date_of_birth, sex, license_number, avatar, status, create_date, update_date, hospital_id)
VALUES
  (3, 'John Doe', '0901234567', 'john@example.com', '1980-01-01', TRUE, 'DL123', NULL, 0, NOW(), NOW(), 1);

-- Ambulances
INSERT INTO ambulances (
  id_ambulance, name, license_plate, description, model_year, status, fuel_type,
  fuel_consumption_per100km, number_of_seats, medical_equipment, oxygen_tank, defibrillator,
  patient_monitor, stretcher, infusion_support, incubator_support,
  gps_locator, camera360, reverse_camera, dash_camera, tpms, impact_sensor,
  speed_warning, air_conditioning, manual_transmission, image, current_location,
  create_date, update_date, brand_ambulance_id, hospital_id, driver_id)
VALUES
  (1, 'Ambulance 1', 'XYZ-001', 'Primary ambulance', 2018, 0, TRUE,
   10.0, 4, TRUE, TRUE, TRUE,
   TRUE, TRUE, FALSE, FALSE,
   TRUE, TRUE, TRUE, TRUE, TRUE, FALSE,
   TRUE, TRUE, TRUE, 'amb1.png', 'Garage',
    NOW(), NOW(), 1, 1, 3);

-- Medical staff
INSERT INTO medical_staff (id_medical_staff, name, phone, email, date_of_birth, sex, license_number, specialization, avatar, status, create_date, update_date, hospital_id)
VALUES
  (1, 'Jane Smith', '0909876543', 'nurse@example.com', '1985-05-05', FALSE, 'MS123', 'Nurse', NULL, 0, NOW(), NOW(), 1);

-- Link medical staff with ambulance
INSERT INTO ambulance_medicalstaff (ambulance_id, medicalstaff_id) VALUES (1,1);

-- Users
INSERT INTO users (id_user, username, password, name_display, email, phone, address, date_of_birth, sex, image, create_date, update_date, id_role)
VALUES
  (1, 'admin', 'password', 'Admin', 'admin@example.com', '0911111111', 'Head Office', '1990-01-01', TRUE, NULL, NOW(), NOW(), 1),
  (2, 'staff', 'password', 'Staff User', 'staff@example.com', '0922222222', 'Office', '1992-02-02', TRUE, NULL, NOW(), NOW(), 2),
  (3, 'driver', 'password', 'Driver User', 'driver@example.com', '0933333333', 'Garage', '1980-03-03', TRUE, NULL, NOW(), NOW(), 3),
  (4, 'medical', 'password', 'Medical User', 'medical@example.com', '0944444444', 'Clinic', '1985-04-04', FALSE, NULL, NOW(), NOW(), 4);

-- Ambulance bookings
INSERT INTO bookings (id_booking, requester_name, phone, pickup_address, destination_address, patient_condition_note, request_time, status, create_date, update_date, ambulance_id, user_id)
VALUES
  (1, 'Patient A', '0955555555', 'Sample Ward', 'Central Hospital', 'Severe headache', NOW(), 0, NOW(), NOW(), 1, 2);
