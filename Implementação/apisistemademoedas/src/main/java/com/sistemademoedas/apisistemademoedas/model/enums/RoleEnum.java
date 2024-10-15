package com.sistemademoedas.apisistemademoedas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ALUNO(1, "ROLE_ALUNO"),
    PROFESSOR(2, "ROLE_PROFESSOR");

    private Integer code;
    private String description;

    //retorna o enum
    public static RoleEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (code.equals(roleEnum.code)) {
                return roleEnum;
            }
        }

        throw new IllegalArgumentException("Code inválido " + RoleEnum.class.getName() + "." + code);
    }
}