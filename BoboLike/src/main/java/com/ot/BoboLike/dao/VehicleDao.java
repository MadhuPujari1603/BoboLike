package com.ot.BoboLike.dao;

import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.User;
import com.ot.BoboLike.dto.Vehicle;
import com.ot.BoboLike.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VehicleDao {

    @Autowired
    private VehicleRepository vehicleRepository;


    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findById(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.orElse(null);
    }

    public Page<Vehicle> findAll(int offset, int pageSize, String field) {
        return vehicleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }

}