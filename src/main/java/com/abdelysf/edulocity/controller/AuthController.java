package com.abdelysf.edulocity.controller;


import com.abdelysf.edulocity.dto.*;
import com.abdelysf.edulocity.service.AuthService;
import com.abdelysf.edulocity.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@Controller
@RequestMapping("api/auth")
public class AuthController {

    final AuthService authService; // DI by constructor , better than @autowired
    final RefreshTokenService refreshTokenService;

    @PostMapping("/signup/student")
    public ResponseEntity<String> signUpStudent(@RequestBody RegisterStudentRequest studentRequest){
        authService.signUpStudent(studentRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", OK);
    }
    @PostMapping("/signup/instructor")
    public ResponseEntity<String> signUpInstructor(@RequestBody RegisterInstructorRequest instructorRequest){
        authService.signUpInstructor(instructorRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", OK);

    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String>  verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<String>("le compte a été activé avec succès  ", OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = authService.login(loginRequest);
        return  new ResponseEntity<>(response, OK);
    }
    @PostMapping("/refresh/token")
    public ResponseEntity<AuthenticationResponse> refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthenticationResponse authenticationResponse = authService.refreshToken(refreshTokenRequest);
        return  new ResponseEntity<>(authenticationResponse, OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
