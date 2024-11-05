package com.sistemademoedas.apisistemademoedas.service.security;


import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemademoedas.apisistemademoedas.exception.VerificationCodeNotValidException;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmailRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.security.ResetPasswordDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.security.TwoFactorCodeDTO;
import com.sistemademoedas.apisistemademoedas.model.security.PasswordResetCode;
import com.sistemademoedas.apisistemademoedas.model.security.User;
import com.sistemademoedas.apisistemademoedas.repository.security.PasswordResetCodeRepository;
import com.sistemademoedas.apisistemademoedas.service.email.EmailService;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void generatePasswordResetCode(EmailRequestDTO emailDTO) {
        var userEmail = emailDTO.email();
        var user = getUserByEmail(userEmail);

        passwordResetCodeRepository.deleteByUser_UserId(user.getUserId());

        int code = generateTwoFactorCode();
        sendPasswordResetCodeToUserEmail(userEmail, code);
        passwordResetCodeRepository.save(new PasswordResetCode(code, user));
    }

    public void validateTwoFactorCode(TwoFactorCodeDTO twoFactorCodeDTO) {
        var user = getUserByEmail(twoFactorCodeDTO.email());
        var passwordResetCode = passwordResetCodeRepository.findByCodeAndUser_UserId(twoFactorCodeDTO.code(), user.getUserId())
                .orElseThrow(() -> new VerificationCodeNotValidException("Código de verificação inválido"));

        if (LocalDateTime.now().isAfter(passwordResetCode.getExpires()))
            throw new VerificationCodeNotValidException("O código de verificação expirou");
    }

    @Transactional
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        var user = getUserByEmail(resetPasswordDTO.email());
        userService.updatePassword(resetPasswordDTO.password(), user);
        deleteExpiredCodes(user.getUserId());
    }

    private int generateTwoFactorCode() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    private void deleteExpiredCodes(Long userId) {
        passwordResetCodeRepository.deleteByUser_UserId(userId);
    }

    private void sendPasswordResetCodeToUserEmail(String email, int code) {
        String subject = "Código de verificação";
        String text = "Digite o seguinte código para conseguir redefinir sua senha: " + code;
        emailService.sendEmail(email, subject, text);
    }

    private User getUserByEmail(String email) {
        return (User) userService.loadUserByUsername(email);
    }
}
