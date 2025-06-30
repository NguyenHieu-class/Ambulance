package com.project.Ambulance.constants;

public interface AppConstants {
	// User Roles
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_MEDICAL_STAFF = 2;
	public static final int ROLE_DRIVER = 3;
	
	// // Trạng thái: 0 = chờ xác nhận, 1 = đang xử lý, 2 = hoàn thành, 3 = hủy
	public static final int STATUS_PENDING = 0;
	public static final int STATUS_IN_PROGRESS = 1;
	public static final int STATUS_COMPLETED = 2;
	public static final int STATUS_CANCELLED = 3;

	// Trạng thái tài xế: 0 = đang rảnh, 1 = đang làm nhiệm vụ, 2 = tạm ngưng hoạt động
	public static final int DRIVER_STATUS_AVAILABLE = 0;
        public static final int DRIVER_STATUS_ON_DUTY = 1;
        public static final int DRIVER_STATUS_SUSPENDED = 2;

        // Trạng thái nhân viên y tế: 0 = rảnh, 1 = đang làm việc, 2 = tạm nghỉ
        public static final int MEDICAL_STATUS_AVAILABLE = 0;
        public static final int MEDICAL_STATUS_ON_DUTY = 1;
        public static final int MEDICAL_STATUS_SUSPENDED = 2;
	
	// Trạng thái hoạt động xe cứu thương: 0 = hoạt động, 1 = bảo trì, 2 = hỏng, 3 = dừng sử dụng
	public static final int AMBULANCE_STATUS_ACTIVE = 0;
	public static final int AMBULANCE_STATUS_MAINTENANCE = 1;
	public static final int AMBULANCE_STATUS_BROKEN = 2;
	public static final int AMBULANCE_STATUS_DECOMMISSIONED = 3;
	
}
