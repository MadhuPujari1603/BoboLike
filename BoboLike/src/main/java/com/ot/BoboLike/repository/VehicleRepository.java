package com.ot.BoboLike.repository;

import com.ot.BoboLike.dto.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    public Vehicle findByVehicleNumberPlate(String vehicleNumberPlate);
}
