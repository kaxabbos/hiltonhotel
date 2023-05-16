package com.hiltonhotel.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    ECONOMY("Эконом"),
    STANDARD("Стандарт"),
    VIP("ВИП"),
    STUDIO("Номер-студия");

    private final String name;
}

