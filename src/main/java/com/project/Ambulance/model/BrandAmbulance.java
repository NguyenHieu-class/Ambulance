package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandAmbulance {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBrand;
	@Column(columnDefinition = "nvarchar(50) not null")
	private String nameBrand;
	private Date createDate;
	private Date updateDate;
	
    @OneToMany(mappedBy = "brandAmbulance" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ambulance> ambulances;
}
