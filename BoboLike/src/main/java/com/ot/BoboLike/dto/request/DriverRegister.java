package com.ot.BoboLike.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRegister {

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String password;

    private String address;

    private String passportNumber;

    private  String licenseNumber;

    private String passportImage;

    private String licenseImage;

    private String nationality;

    private long vehicleId;
}
