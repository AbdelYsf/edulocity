package com.abdelysf.edulocity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInstructorRequest {

    private  String firstName;
    private  String lasName;
    private  String email;
    private  String userName;
    private  String password;
    private String gender;
    private  String specialisation;
    private String bio;

}
