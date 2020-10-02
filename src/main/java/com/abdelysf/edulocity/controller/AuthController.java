package com.abdelysf.edulocity.controller;


import com.abdelysf.edulocity.dto.RegisterStudentRequest;
import com.abdelysf.edulocity.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("api/auth")
public class AuthController {

    final AuthService authService; // DI by constructor , better than @autowired


//    public ResponseEntity signUpStudent(@RequestBody RegisterStudentRequest studentRequest){
//
//
//    }
}
