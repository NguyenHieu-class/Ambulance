package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
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
    private String License;

    @Column(columnDefinition = "nvarchar(100)")
    private String ImgLicense;

    private Date createDate;
    private Date updateDate;


}
