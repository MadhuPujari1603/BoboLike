package com.ot.BoboLike.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInitiate {

    private double pickupLocationlatitude;

    private double pickupLocationlongitude;

    private double dropLocationLatitude;

    private double dropLocationLongitude;

    private String customerName;

    private String customerNumber;

    private long driverId;
}