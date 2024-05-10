package com.example.sisrec.enums;


public enum UsuarioRole {
    ADMIN(0, "ADMIN"), USUARIO(1, "USUARIO");

    private final Integer codigo;
    private final String role;

    UsuarioRole(Integer codigo, String role) {
        this.codigo = codigo;
        this.role = role;
    }

   public String getRole(){
        return role;
   }


}
