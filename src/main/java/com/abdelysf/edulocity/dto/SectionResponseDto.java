package com.abdelysf.edulocity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SectionResponseDto {
    private Long sectionId;
    private  String sectionName;
    private String description;
}
