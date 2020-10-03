package com.abdelysf.edulocity.service;


import com.abdelysf.edulocity.dto.AuthenticationResponse;
import com.abdelysf.edulocity.dto.LoginRequest;
import com.abdelysf.edulocity.dto.RegisterInstructorRequest;
import com.abdelysf.edulocity.dto.RegisterStudentRequest;
import com.abdelysf.edulocity.exceptions.EduLocityException;
import com.abdelysf.edulocity.model.*;
import com.abdelysf.edulocity.repository.IStudentDAO;
import com.abdelysf.edulocity.repository.IUserDAO;
import com.abdelysf.edulocity.repository.IVerificationTokenDAO;
import com.abdelysf.edulocity.repository.InstructorDAO;
import com.abdelysf.edulocity.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final IStudentDAO studentRepository;
    private final InstructorDAO instructorRepository;
    private final IVerificationTokenDAO verificationTokenRepository;
    private final IUserDAO userRepository;
    private final MailService mailService;
    private  final AuthenticationManager authenticationManager;
    private  final JwtProvider jwtProvider;



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

    /**
     * verify that a passed token is valid and enable the user associated to it
     * @param token
     */
    public void verifyAccount(String token) {
        Optional<VerificationToken> byToken = verificationTokenRepository.findByToken(token);
        byToken.orElseThrow(()-> new EduLocityException("invalid token"));
        fetchUserAndEnable(byToken.get());
    }

    /**
     * enable the user
     * @param verificationToken
     */
    @Transactional
    public  void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUserName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new EduLocityException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        // storing the authentication object inside the security context

        SecurityContextHolder.getContext().setAuthentication(authenticate); // further we can just look in the context to see whether the user is login or not
        // generating the token
        String token = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(token,loginRequest.getUserName());


    }
}
