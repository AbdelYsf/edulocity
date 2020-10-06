package com.abdelysf.edulocity.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder // used with @Singler to implement builder pattern
@Slf4j // generate a logger accessed by log.xxx()
public class PartDto {
    private  Long id;
    private  String partName;
    private  String description;
    private  String FileType;
    private  String filePath;
}
