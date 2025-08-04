package com.riquelmemr.simpletweet.enums;

public enum RoleEnum {
    ADMIN(1),
    BASIC(2);

    final int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}