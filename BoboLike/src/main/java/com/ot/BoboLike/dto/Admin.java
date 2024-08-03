package com.ot.BoboLike.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    {
        super.setRole("ROLE_ADMIN");
    }

    private String firstName;

    private String lastName;

}