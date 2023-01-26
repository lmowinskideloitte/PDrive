package pl.put.poznan.pdrive.entity;

import lombok.ToString;

@ToString
public enum UserTypeE {

    USER("USER"),
    ADMIN("ADMIN");

    final String value;

    UserTypeE(String value) {
        this.value = value;
    }
}
