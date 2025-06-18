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

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@Column(name = "username", columnDefinition = "nvarchar(30) not null", unique = true)
	private String username;
	
	@Column(name = "password", columnDefinition = "nvarchar(200) not null")
	private String password;
	
	@Column(columnDefinition = "nvarchar(200) not null")
	private String nameDisplay;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String image;
	
	@Column(columnDefinition = "nvarchar(12)")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(30)")
	private String email;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String address;
	
	@Column(columnDefinition = "nvarchar(12)")
	private String dateOfBrith;
	
	@Column(columnDefinition = "nvarchar(20)")
	private String drivingLicense;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String ImgDrivingLicense; 
	
	private boolean sex;
	
        private Date createDate;
        private Date updateDate;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_role")
        @JsonIgnore
        private Role role;

}
