package com.project.Ambulance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    // Tên vai trò: ADMIN, STAFF, etc.
    @Column(columnDefinition = "nvarchar(30) not null", unique = true)
    private String name;

    // Mô tả vai trò (tùy chọn)
    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    // Danh sách người dùng thuộc vai trò này
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<User> users;

    public Role(int idRole) {
		this.idRole = idRole;
	}
}
