package com.hiltonhotel.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Директор"),
    SELLER("Управляющий"),
    BUYER("Клиент");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

