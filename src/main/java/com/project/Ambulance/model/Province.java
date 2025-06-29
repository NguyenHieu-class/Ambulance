package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProvince;
	@Column(columnDefinition = "nvarchar(70) not null")
	private String nameProvince;
	private int zipCode;
	private String imgProvince;
	private Date createDate;
	private Date updateDate;
	
	@OneToMany(mappedBy = "province" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private List<District> districts;
}
