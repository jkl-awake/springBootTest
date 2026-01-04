package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
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
    
    @EnumValue
    private final int code = 1;
    
    public int getCode() {
        return value;
    }
    
    public static PlatformEnum fromCode(int code) {
        for (PlatformEnum platform : PlatformEnum.values()) {
            if (platform.getCode() == code) {
                return platform;
            }
        }
        throw new IllegalArgumentException("Invalid PlatformEnum code: " + code);
    }

    PlatformEnum(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }
}
