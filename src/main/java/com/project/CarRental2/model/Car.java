package com.project.Ambulance.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ambulance {
        public Ambulance(int id) {
                this.id=id;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

	@Column(columnDefinition = "nvarchar(50) not null")
        private String name;

	@Column(columnDefinition = "nvarchar(4000)")
        private String overview;

        // medical equipment
        private int oxygenTanks;
        private int mobileBeds;

        @Column(columnDefinition = "nvarchar(2000)")
        private String medications;

	@Column(columnDefinition = "nvarchar(20)", unique = true)
        private String licensePlates;

        private int modelYear;
        private int status;

        // other specifications
        private int maximumKilometersperDay;

	@Column(columnDefinition = "nvarchar(2000)")
        private String notes;

	@Column(columnDefinition = "nvarchar(1000)")
        private String address;

	@Column(columnDefinition = "nvarchar(200)")
        private String avatar;

	@Column(columnDefinition = "nvarchar(2000)")
        private String images;

	
	// số ghế ngồi
	private int numberOfSeats;

	private boolean bluetooth;
	// camerea hànhtrinhf
	private boolean dashCamera;
	// camera lùi
	private boolean reverseCamera;
	// camera 360
	private boolean camera360;
	// cam cập lề
	private boolean parkingCamera;
	// cảm biến ám suât lopps
	private boolean tpms;
	// cảnh báo tốc độ
	private boolean speedWarning;
	// gps
	private boolean gpsLocator;
	// Cửa sổ trời
	private boolean sunroof;
	// màn hình dvd
	private boolean dvdScreen;
	// tự lái hoặc có tài
	private boolean driver;
	// map
	private boolean maps;
	// ghế tre em
	private boolean babyseat;
	// lốp dự phòng
	private boolean spareTire;
	// usb
	private boolean usb;
	// cảnh báo va chạm
	private boolean impactSensor;
	// nắp thùng xe bán tải
	private boolean bonnet;
	// thu phí không dừng
	private boolean etc;
	// túi khí an toàn
	private boolean airbags;
	// số sàn hoặc tự dộng
	private boolean manualTransmissionCar;

	private Date createDate;
	private Date updateDate;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "driver_id")
        @JsonIgnore
        private User driver;
}
