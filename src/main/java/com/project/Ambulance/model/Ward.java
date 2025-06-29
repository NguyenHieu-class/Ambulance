package com.project.Ambulance.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idWard;
	@Column(columnDefinition = "nvarchar(150) not null")
	private String nameWard;
	private Date createDate;
	private Date updateDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_district")
	private District district;
	public Ward(int idWard, String nameWard) {
		this.idWard = idWard;
		this.nameWard = nameWard;
	}
}
