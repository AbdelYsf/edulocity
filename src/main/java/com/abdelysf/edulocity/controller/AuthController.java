package com.abdelysf.edulocity.controller;


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
    public ResponseEntity signUpStudent(@RequestBody RegisterStudentRequest studentRequest){
        authService.signUpStudent(studentRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", HttpStatus.OK);
    }
    @PostMapping("/signup/instructor")
    public ResponseEntity signUpInstructor(@RequestBody RegisterInstructorRequest instructorRequest){
        authService.signUpInstructor(instructorRequest);
        return new ResponseEntity<String>("l'inscription s'est deroulé avec succes vérifier votre email pour activer votre compte ", HttpStatus.OK);

    }


}
