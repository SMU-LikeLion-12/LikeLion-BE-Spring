package com.likelion.lionshop_sample.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserStatus {

    WITHDRAWAL(0),
    ACTIVE(1),
    PAUSED(2),
    ;

    int status;

}
