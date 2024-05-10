package com.example.sisrec.models;

import com.example.sisrec.enums.UsuarioRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLE")
@Getter
@Setter
public class RoleModel implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UsuarioRole userRole;



    @Override
    public String getAuthority() {
        return this.userRole.toString();
    }


}
