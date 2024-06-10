package com.example.sisrec.enums;




public enum StatusReclamacao {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), CONCLUIDO(2, "CONCLUIDO");

    private Integer codigo;
    private String descricao;

    StatusReclamacao(Integer codigo, String descricao) {
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
            throw new IllegalArgumentException("Reclamação iválida");
        }

}
