package com.hiltonhotel.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CartStatus {
    WAITING("Ожидание"),
    REFUSED("Отказано"),
    APPROVED("Одобрено");

    private final String name;
}

