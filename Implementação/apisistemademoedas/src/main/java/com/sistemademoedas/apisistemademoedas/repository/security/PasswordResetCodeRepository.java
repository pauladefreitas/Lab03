package com.sistemademoedas.apisistemademoedas.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemademoedas.apisistemademoedas.model.security.PasswordResetCode;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {

    Optional<PasswordResetCode> findByCodeAndUser_UserId(int code, Long userId);

    void deleteByUser_UserId(Long userId);
}