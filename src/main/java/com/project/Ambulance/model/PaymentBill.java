package com.project.Ambulance.model;

import java.util.Date;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaymentBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trip")
    private Trip trip;

    private double amount;

    private Date paymentDate;

    @Column(columnDefinition = "nvarchar(30)")
    private String status;
}
