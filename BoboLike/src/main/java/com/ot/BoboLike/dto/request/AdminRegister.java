package com.ot.BoboLike.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegister {

    private String name;

    private String phone;

    private String email;

    private String password;

    private String address;
}