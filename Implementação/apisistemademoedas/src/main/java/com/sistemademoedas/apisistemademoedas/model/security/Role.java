package com.sistemademoedas.apisistemademoedas.model.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    ADMIN {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    },
    CLIENT {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
    };

    public abstract Collection<? extends GrantedAuthority> getSimpleGrantedAuthority();
}