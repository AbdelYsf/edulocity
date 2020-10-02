package com.abdelysf.edulocity.model;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Data  @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private  Long id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String phoneNumber;
    private  String userName;
    private  int age;
    private  String city;
    private  String country;
    private  String gender;
    private  String password;
    private  boolean isEnabled;
    private Instant created ;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Role> roles;

    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration; // date d'inscreption



}
