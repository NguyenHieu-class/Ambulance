package com.project.Ambulance.util;

import com.project.Ambulance.constants.AppConstants;

import java.util.LinkedHashMap;
import java.util.Map;

public final class StatusMappingUtil {

    private StatusMappingUtil() {
        // Utility class
    }

    public static Map<Integer, String> ambulanceStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.AMBULANCE_STATUS_ACTIVE, "Hoạt động");
        map.put(AppConstants.AMBULANCE_STATUS_MAINTENANCE, "Bảo trì");
        map.put(AppConstants.AMBULANCE_STATUS_BROKEN, "Hỏng");
        map.put(AppConstants.AMBULANCE_STATUS_DECOMMISSIONED, "Dừng sử dụng");
        return map;
    }

    public static Map<Integer, String> driverStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.DRIVER_STATUS_AVAILABLE, "Đang rảnh");
        map.put(AppConstants.DRIVER_STATUS_ON_DUTY, "Đang làm nhiệm vụ");
        map.put(AppConstants.DRIVER_STATUS_SUSPENDED, "Tạm ngưng hoạt động");
        return map;
    }

    public static Map<Integer, String> medicalStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.MEDICAL_STATUS_AVAILABLE, "Rảnh");
        map.put(AppConstants.MEDICAL_STATUS_ON_DUTY, "Đang làm việc");
        map.put(AppConstants.MEDICAL_STATUS_SUSPENDED, "Tạm nghỉ");
        return map;
    }

    public static Map<Integer, String> bookingStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.STATUS_PENDING, "Chờ xác nhận");
        map.put(AppConstants.STATUS_IN_PROGRESS, "Đang xử lý");
        map.put(AppConstants.STATUS_COMPLETED, "Hoàn thành");
        map.put(AppConstants.STATUS_CANCELLED, "Hủy");
        return map;
    }
}
