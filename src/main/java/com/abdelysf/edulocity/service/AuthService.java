package com.abdelysf.edulocity.service;


import com.abdelysf.edulocity.dto.RegisterInstructorRequest;
import com.abdelysf.edulocity.dto.RegisterStudentRequest;
import com.abdelysf.edulocity.model.*;
import com.abdelysf.edulocity.repository.IStudentDAO;
import com.abdelysf.edulocity.repository.IVerificationTokenDAO;
import com.abdelysf.edulocity.repository.InstructorDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final IStudentDAO studentRepository;
    private final InstructorDAO instructorRepository;
    private final IVerificationTokenDAO verificationTokenRepository;
    private final MailService mailService;



    @Transactional
    public void signUpStudent(RegisterStudentRequest studentRequest){
        //mapping the request DTO with an instance of student
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLasName());
        student.setUserName(studentRequest.getUserName());
        student.setAge(studentRequest.getAge());
        //encoding the password
        student.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        student.setGender(studentRequest.getGender());
        student.setCne(studentRequest.getCne());
        student.setCreated(Instant.now());
        student.setEnabled(false);
        //saving the student
        studentRepository.save(student);
        //get token
        String token = generateVerificationToken(student);
        //send verification email
        sendMailToUser(student,token);


    }

    @Transactional
    public void signUpInstructor(RegisterInstructorRequest instructorRequest){
        //mapping the request DTO with an instance of Instructor
        Instructor instructor = new Instructor();
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLasName());
        instructor.setUserName(instructorRequest.getUserName());
        instructor.setPassword(passwordEncoder.encode(instructorRequest.getPassword()));
        instructor.setGender(instructorRequest.getGender());
        instructor.setBio(instructorRequest.getBio());
        Specialisation specialisation = Specialisation.valueOf(instructorRequest.getSpecialisation());
        instructor.setSpecialisation(specialisation);
        //saving the instructor
        instructorRepository.save(instructor);
        //get token
        String token = generateVerificationToken(instructor);
        //send verification email
        sendMailToUser(instructor,token);

    }

    /**
     * generate a random token and associate it with user passed as parameter
     * @param user :the user with whom the token will be associated with
     * @return random token
     */
    private String generateVerificationToken(User user){

        // using UUID utility
        String token = UUID.randomUUID().toString();
        //instance of verificationToken class
        VerificationToken verificationToken = new VerificationToken();
        //associate with token and the user
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        //save the verificationToken instance in the db
        verificationTokenRepository.save(verificationToken);

        return token;
    }

    /**
     * send the verification email to the user
     * @param user
     * @param token
     */

    private void sendMailToUser(User user , String token ){

         final String endpointUrl="http://localhost:8080/api/auth/accountVerification/"+token;
         final String messageBody="on vous remercie pour l'inscription dans Edulocity " +
                 ",veuillez completer votre inscription en cliquant sur le lien ci-dessous :\n";

        mailService.sendMail(new NotificationEmail("activation de votre compte"
                ,user.getEmail()
                ,messageBody+endpointUrl
                ));
    }


}
