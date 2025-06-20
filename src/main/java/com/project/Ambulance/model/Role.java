package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    @Column(columnDefinition = "nvarchar(200) not null")
    private String nameRole;
    private Date createDate;
    private Date updateDate;
    // mapped by is mapping name class
    @OneToMany(mappedBy = "role" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;

    public Role(int idRole) {
        this.idRole = idRole;
    }
}
