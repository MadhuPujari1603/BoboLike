package com.ot.BoboLike.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double pickupLocationlatitude;

    private double pickupLocationlongitude;

    private double dropLocationLatitude;

    private double dropLocationLongitude;

    private String customerName;

    private String customerNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Driver driver;
}