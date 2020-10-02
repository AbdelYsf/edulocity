package com.abdelysf.edulocity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudentRequest {

   private  String firstName;
   private  String lasName;
   private  String email;
   private  String userName;
   private  int age;
   private  String cne;
   private  String password;
   private  String gender;


}
