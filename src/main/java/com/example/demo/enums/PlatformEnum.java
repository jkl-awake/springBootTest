package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum PlatformEnum {
    PC(1, "PC"),
    XBOX(2, "Xbox"),
    PLAYSTATION(3, "PlayStation"),
    SWITCH(4, "Switch"),
    MOBILE(5, "Mobile");

    private final int value;
    private final String displayName;

    PlatformEnum(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }
}
