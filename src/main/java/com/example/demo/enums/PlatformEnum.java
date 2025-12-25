package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlatformEnum {
    PC(1, "PC"),
    XBOX(2, "Xbox"),
    PLAYSTATION(3, "PlayStation"),
    SWITCH(4, "Switch"),
    MOBILE(5, "Mobile");

    private final int value;
    private final String displayName;
}
