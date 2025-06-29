package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDistrict;
	@Column(columnDefinition = "nvarchar(60) not null")
	private String nameDistrict;
	private Date createDate;
	private Date updateDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_province")
	private Province province;
	
	@OneToMany(mappedBy = "district" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Ward> wards;

	public District(int idDistrict, String nameDistrict) {
		super();
		this.idDistrict = idDistrict;
		this.nameDistrict = nameDistrict;
		
	}
	
	public String convertJson() {
		String result= "{\"idDistrict\":\""+idDistrict+"\",\"nameDistric\": \""+nameDistrict+"\"}";
		return result;
	}
}
