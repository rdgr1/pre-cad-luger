package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class Users implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "name")
    private String name;


    @Column(name = "cpf")
    private String cpf;


    @Column(name = "number")
    private String number;


    @Column(name = "primary_access")
    private Boolean primary_access;


    @Column(name = "admin")
    private Boolean admin;

    @Column(name = "enabled")
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", number='" + number + '\'' +
                ", primary_access=" + primary_access +
                ", admin=" + admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users userModel = (Users) o;
        return Objects.equals(id, userModel.id) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(name, userModel.name) && Objects.equals(cpf, userModel.cpf) && Objects.equals(number, userModel.number) && Objects.equals(primary_access, userModel.primary_access) && Objects.equals(admin, userModel.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, cpf, number, primary_access, admin);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getPrimary_access() {
        return primary_access;
    }

    public void setPrimary_access(Boolean primary_access) {
        this.primary_access = primary_access;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implemente a lógica para verificar se a conta do usuário não está expirada
        return true; // retorno de exemplo
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implemente a lógica para verificar se a conta do usuário não está bloqueada
        return true; // retorno de exemplo
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implemente a lógica para verificar se as credenciais do usuário não estão expiradas
        return true; // retorno de exemplo
    }

    @Override
    public boolean isEnabled() {
        // Implemente a lógica para verificar se a conta do usuário está habilitada
        return enabled; // retorno de exemplo
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
