package com.abdelysf.edulocity.dto;

import com.abdelysf.edulocity.model.Part;
import lombok.*;

import java.util.List;

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
    private  List<PartDto> parts;
}
