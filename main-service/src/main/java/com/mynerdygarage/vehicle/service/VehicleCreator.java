package com.mynerdygarage.vehicle.service;

import com.mynerdygarage.util.CustomFormatter;
import com.mynerdygarage.vehicle.dto.NewVehicleDto;
import com.mynerdygarage.vehicle.model.Vehicle;

public class VehicleCreator {

    public static Vehicle create(Long ownerId, NewVehicleDto newVehicleDto) {

        Vehicle vehicle = new Vehicle();

        vehicle.setOwnerId(ownerId);
        vehicle.setProducer(newVehicleDto.getProducer());
        vehicle.setModel(newVehicleDto.getModel());

        String name;
        if (newVehicleDto.getName() == null) {
            name = newVehicleDto.getProducer() + " " + newVehicleDto.getModel();
        } else {
            name = newVehicleDto.getName();
        }
        vehicle.setName(name);

        vehicle.setColor(newVehicleDto.getColor());
        vehicle.setRegNumber(newVehicleDto.getRegNumber());
        vehicle.setReleaseDate(CustomFormatter.stringToDate(newVehicleDto.getReleaseDate()));
        vehicle.setEngineVolume(newVehicleDto.getEngineVolume());

        String fuelType;
        if (newVehicleDto.getFuelType() == null) {
            fuelType = null;
        } else {
            fuelType = newVehicleDto.getFuelType().toString();
        }
        vehicle.setFuelType(fuelType);

        vehicle.setPower(newVehicleDto.getPower());
        vehicle.setDescription(newVehicleDto.getDescription());

        return vehicle;
    }
}
