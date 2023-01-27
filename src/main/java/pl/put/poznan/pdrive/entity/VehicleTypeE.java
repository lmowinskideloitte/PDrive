package pl.put.poznan.pdrive.entity;

import lombok.ToString;

@ToString
public enum VehicleTypeE {

    BICYCLE("BICYCLE"),
    SCOOTER("SCOOTER"),
    MOTORCYCLE("MOTORCYCLE");

    final String value;

    VehicleTypeE(String value) {
        this.value = value;
    }
}
