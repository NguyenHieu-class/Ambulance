package com.project.Ambulance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    private String nameRole;

    // getters and setters
    public int getIdRole() { return idRole; }
    public void setIdRole(int idRole) { this.idRole = idRole; }
    public String getNameRole() { return nameRole; }
    public void setNameRole(String nameRole) { this.nameRole = nameRole; }
}
