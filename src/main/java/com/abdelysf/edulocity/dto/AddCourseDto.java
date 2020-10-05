package com.abdelysf.edulocity.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddCourseDto {

   private String title;
   private String description;
   private String prerequisites;
   private String level;
   private String estimatedTime;
   private String price;
   private MultipartFile image;
}
