package com.example.javacro;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String username, password, email;

    UserAccount() {
    }

    UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Transient
    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override public boolean isEnabled() {
        return true;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        return password;
    }
}
