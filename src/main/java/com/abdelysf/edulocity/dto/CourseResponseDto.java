package com.abdelysf.edulocity.dto;

import com.abdelysf.edulocity.model.Section;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CourseResponseDto {

    private Integer courseId;
    private String title;
    private String description;
    private String prerequisites;
    private String level;
    private String estimatedTime;
    private String price;
    private String imagePath;
    private String category;
    private String instructorName;
    private String sectionCount;

}
