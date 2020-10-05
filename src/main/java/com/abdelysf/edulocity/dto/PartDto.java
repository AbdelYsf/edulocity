package com.abdelysf.edulocity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PartDto {
    private  Long id;
    private  String partName;
    private  String description;
    private  String FileType;
    private  String filePath;
}
