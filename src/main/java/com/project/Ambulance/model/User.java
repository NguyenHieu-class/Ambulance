package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
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

    @Column(columnDefinition = "nvarchar(100) not null")
    private String nameDisplay;

    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    @Column(columnDefinition = "nvarchar(20)")
    private String phone;

    @Column(columnDefinition = "nvarchar(200)")
    private String address;

    @Column(columnDefinition = "nvarchar(12)")
    private String dateOfBirth;

    private boolean sex;

    @Column(columnDefinition = "nvarchar(255)")
    private String image;

    private Date createDate;
    private Date updateDate;

    // === Vai trò người dùng (ADMIN, STAFF...) ===
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    @JsonIgnore
    private Role role;

    // === Danh sách các yêu cầu cấp cứu mà user này đã tạo ===
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Booking> bookings;
}
