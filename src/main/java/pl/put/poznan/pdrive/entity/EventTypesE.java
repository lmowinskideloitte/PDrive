package pl.put.poznan.pdrive.entity;

import lombok.ToString;

@ToString
public enum EventTypesE {

    CRASH("CRASH"),
    MALFUNCTION("MALFUNCTION");

    final String value;

    EventTypesE(String value) {
        this.value = value;
    }

}
