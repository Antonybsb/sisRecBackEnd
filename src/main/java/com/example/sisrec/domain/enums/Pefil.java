package com.example.sisrec.domain.enums;

import lombok.Getter;
import lombok.Setter;


public enum Pefil {
    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE");

    private Integer codigo;
    private String descricao;

    Pefil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusReclamacao toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for(StatusReclamacao x : StatusReclamacao.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil iválido");
    }
}
