package com.abdelysf.edulocity.model;

import lombok.*;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Admin extends User {
    private boolean isSuperAdmin;

}
