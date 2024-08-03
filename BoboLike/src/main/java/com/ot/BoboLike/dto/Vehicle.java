package com.ot.BoboLike.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vehicleName;

    @Column(unique = true)
    private String vehicleNumberPlate;

    private String modelOfYear;

    private String vehicleType;

    private String fuelType;

    private String occupancy;

    @OneToOne(mappedBy = "vehicle")
    @JsonBackReference("vehicle")
    private Driver driver;

}
