package com.example.sisrec.models;

import com.example.sisrec.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "TB_USUARIOS")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPessoa;
    private String login;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String password;
    private UserRole role;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDate();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReclamacaoModel> reclamacoes;

    public UsuarioModel(String login, String cpf, String email, String password, UserRole role, LocalDate dataCriacao){
        this.login = login;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USUARIO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}