package com.abdelysf.edulocity.controller;


import com.abdelysf.edulocity.dto.AuthenticationResponse;
import com.abdelysf.edulocity.dto.LoginRequest;
import com.abdelysf.edulocity.dto.RegisterInstructorRequest;
import com.abdelysf.edulocity.dto.RegisterStudentRequest;
import com.abdelysf.edulocity.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("api/auth")
public class AuthController {

    final AuthService authService; // DI by constructor , better than @autowired

    @PostMapping("/signup/student")
    public ResponseEntity<String> signUpStudent(@RequestBody RegisterStudentRequest studentRequest){
        authService.signUpStudent(studentRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", HttpStatus.OK);
    }
    @PostMapping("/signup/instructor")
    public ResponseEntity<String> signUpInstructor(@RequestBody RegisterInstructorRequest instructorRequest){
        authService.signUpInstructor(instructorRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", HttpStatus.OK);

    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String>  verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<String>("le compte a été activé avec succès  ",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = authService.login(loginRequest);
        return  new ResponseEntity<AuthenticationResponse>(response,HttpStatus.OK);
    }
}
