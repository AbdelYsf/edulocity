package com.abdelysf.edulocity.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddPartRequest {

    private  Long sectionId;
    private  String partName;
    private  String description;
    private  String FileType;
    private MultipartFile file;

}
