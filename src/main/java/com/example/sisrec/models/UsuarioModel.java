package com.example.sisrec.models;

import com.example.sisrec.enums.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "TB_USUARIOS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPessoa;
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String password;
    private UsuarioRole role = UsuarioRole.USUARIO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDate();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 10)  // Adicionando BatchSize para otimizar o carregamento das reclamações
    private List<ReclamacaoModel> reclamacoes;

    public UsuarioModel(String nome, String cpf, String email, String password) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    //Método para atualizar os atributos do usuário com exceção de idPessoa, cpf, e dataCriacao.
    public void update(UsuarioModel source) {
        this.nome = source.nome;
        this.email = source.email;
        this.password = source.password;
        this.role = source.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USUARIO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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