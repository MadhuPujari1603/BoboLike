package com.ot.BoboLike.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRegister {

    private String vehicleName;

    private String vehicleNumber;

    private String modelOfYear;

    private String vehicleType;

    private String fuelType;

    private String occupancy;
}
