package com.ot.BoboLike.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver extends User {

    {
        super.setRole("ROLE_DRIVER");
    }

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = true)
    private String passportNumber;

    private String passportImage;

    @Column(unique = true, nullable = true)
    private String licenseNumber;

    private String licenseImage;

    private String nationality;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference("vehicle")
    @JoinColumn
    private Vehicle vehicle;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = DriverStatus.AVAILABLE;
        }
    }

}