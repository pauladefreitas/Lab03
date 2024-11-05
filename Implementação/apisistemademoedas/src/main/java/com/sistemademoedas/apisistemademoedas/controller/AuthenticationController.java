package com.sistemademoedas.apisistemademoedas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemademoedas.apisistemademoedas.model.dto.request.EmailRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.security.AuthenticationDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.security.ResetPasswordDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.security.TwoFactorCodeDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.security.LoginResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.security.User;
import com.sistemademoedas.apisistemademoedas.service.security.PasswordResetService;
import com.sistemademoedas.apisistemademoedas.service.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        var authentication = authenticationManager.authenticate(authToken);

        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);

        return ResponseEntity.ok().body(new LoginResponseDTO(user.getUserId(), tokenJWT, user.getRole()));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid EmailRequestDTO emailDTO) {
        passwordResetService.generatePasswordResetCode(emailDTO);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Void> verifyTwoFactorCode(@RequestBody @Valid TwoFactorCodeDTO twoFactorCodeDTO) {
        passwordResetService.validateTwoFactorCode(twoFactorCodeDTO);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) {
        passwordResetService.resetPassword(resetPasswordDTO);
        return ResponseEntity.noContent().build();
    }
}
