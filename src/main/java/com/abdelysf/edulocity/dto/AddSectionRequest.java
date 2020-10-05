package com.abdelysf.edulocity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddSectionRequest {
    private int courseId;
    private String sectionName;
    private String description;
}
