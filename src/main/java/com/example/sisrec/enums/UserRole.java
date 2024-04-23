package com.example.sisrec.enums;


public enum UserRole {
    ADMIN(0, "ADMIN"), USUARIO(1, "USUARIO");

    private Integer codigo;
    private String role;

    UserRole(Integer codigo, String role) {
        this.codigo = codigo;
        this.role = role;
    }

   public String getRole(){
        return role;
   }


}
