package com.sistemademoedas.apisistemademoedas.model.security;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_password_reset_codes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private LocalDateTime expires;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public PasswordResetCode(int code, User user) {
        this.code = code;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        this.expires = LocalDateTime.now().plusMinutes(5);
    }
}