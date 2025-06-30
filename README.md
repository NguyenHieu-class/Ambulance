# Ambulance Management

Ứng dụng **Ambulance Management** là dự án Spring Boot dùng để quản lý hệ thống xe cứu thương cùng các thực thể liên quan như bệnh viện, tài xế, nhân viên y tế, đơn đặt xe và vùng địa lý. Ứng dụng cung cấp giao diện web lẫn API đơn giản để quản trị và theo dõi trạng thái hoạt động.

## Mục lục
1. [Các tính năng chính](#cac-tinh-nang-chinh)
2. [Cấu trúc dự án](#cau-truc-du-an)
3. [Thiết lập môi trường](#thiet-lap-moi-truong)
4. [Khởi chạy ứng dụng](#khoi-chay-ung-dung)
5. [Dữ liệu mẫu](#du-lieu-mau)
6. [Chạy kiểm thử](#chay-kiem-thu)

## Các tính năng chính
- **Quản lý xe cứu thương**  
  Thêm, chỉnh sửa, xóa xe; cập nhật trạng thái hoạt động; gán tài xế, bệnh viện và thương hiệu.
- **Quản lý bệnh viện, tài xế và nhân viên y tế**  
  Lưu trữ thông tin chi tiết, ảnh đại diện, trạng thái làm việc.
- **Quản lý đặt xe (booking)**  
  Lưu lịch sử điều động xe, trạng thái thực hiện và danh sách nhân viên y tế tham gia.
- **Quản lý khu vực (tỉnh/thành, quận/huyện, phường/xã)**  
  Dùng để xác định địa điểm phục vụ cho bệnh viện, người đặt xe.
- **Dashboard cho từng vai trò**  
  - Admin: thống kê tổng quan và các trang quản trị.  
  - Driver/Medical: xem lịch làm việc, chỉnh sửa hồ sơ cá nhân.
- **Đăng nhập, đăng ký đơn giản**  
  Chưa sử dụng Spring Security, phiên đăng nhập được lưu trong `HttpSession`.
- **Upload tệp**  
  Lưu trữ ảnh đại diện hoặc ảnh xe tại thư mục `uploads/`.

## Cấu trúc dự án
```text
src/
 └── main/
     ├── java/com/project/Ambulance/
     │   ├── controller/   # Các controller cho API và giao diện
     │   ├── model/        # Entity JPA
     │   ├── repository/   # Repository dùng Spring Data JPA
     │   ├── service/      # Service và triển khai
     │   ├── util/         # Lớp tiện ích (mapping trạng thái, upload file, …)
     │   └── configuration/ # Cấu hình MVC
     └── resources/
         ├── templates/    # Thymeleaf templates
         ├── static/       # CSS/JS tĩnh
         ├── application.properties # cấu hình kết nối MySQL
         └── data.sql      # dữ liệu mẫu
```

Trong thư mục `src/test` có một vài lớp kiểm thử sử dụng `WebMvcTest` cho một số controller.

## Thiết lập môi trường
- **Java**: yêu cầu Java 17 trở lên (pom sử dụng `java.version 24`).  
- **Maven**: đã kèm Maven Wrapper, có thể dùng `./mvnw`.  
- **MySQL**: tạo database `ambulance_management`. Cập nhật tài khoản trong `src/main/resources/application.properties`.

Ví dụ cấu hình:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambulance_management?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Ứng dụng mặc định chạy ở cổng `8083`.

## Khởi chạy ứng dụng
Sau khi đã cấu hình database:
```bash
./mvnw spring-boot:run
```
Hoặc biên dịch thành jar:
```bash
./mvnw clean package
java -jar target/Ambulance-0.0.1-SNAPSHOT.jar
```
Truy cập `http://localhost:8083` để vào trang đăng nhập.

## Dữ liệu mẫu
File [`src/main/resources/data.sql`](src/main/resources/data.sql) cung cấp dữ liệu mẫu cho toàn bộ bảng (vai trò, người dùng, tỉnh thành, bệnh viện, tài xế, xe cứu thương, …).  
Nếu muốn nạp lại dữ liệu, bạn có thể xóa dữ liệu cũ và khởi động lại ứng dụng.

Để Spring Boot tự động chạy script, bỏ comment ở cấu hình:
```properties
spring.sql.init.mode=always
```

## Chạy kiểm thử
Dự án có một số bài test dùng JUnit và Spring Boot Test. Thực thi bằng:
```bash
./mvnw test
```
