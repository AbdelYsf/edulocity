package com.abdelysf.edulocity.mapper;


import com.abdelysf.edulocity.dto.AddCourseDto;
import com.abdelysf.edulocity.dto.CourseResponseDto;
import com.abdelysf.edulocity.dto.PartDto;
import com.abdelysf.edulocity.dto.SectionResponseDto;
import com.abdelysf.edulocity.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    Course mapDtoToCourse(AddCourseDto addCourseDto);
     // if we need to inject a field , we can change the interface to abstract class
    @Mapping(target ="sectionCount" ,expression = "java(mapSectionsCount(course.getSections()))")
    @Mapping(target ="instructorName",expression = "java(mapInstructorName(course.getInstructor()))")
    @Mapping(target ="category",expression = "java(mapCategoryName(course.getCategory()))")
    @Mapping(target = "courseId", source = "id")
    @Mapping(target ="sections" , expression = "java(mapSections(course.getSections()))")
    CourseResponseDto mapCourseToResponseDto(Course course);


    default String mapSectionsCount(Collection<Section> sections){

        return String.valueOf( sections.size());
    }
    default String mapInstructorName(Instructor instructor){
        return instructor.getFirstName().concat(" "+instructor.getLastName());
    }

    default String mapCategoryName(Category Category){
        return Category.getCategoryName();
    }

    default List<SectionResponseDto> mapSections(Collection<Section> sections){
        List<SectionResponseDto>  list = new ArrayList<>();
        SectionResponseDto sectionDto=null;
        for (Section s : sections) {
            sectionDto = new SectionResponseDto();
            sectionDto.setSectionId((s.getId()));
            sectionDto.setSectionName(s.getSectionName());
            sectionDto.setDescription(s.getDescription());
            // add parts
            PartDto partDto=null;
            for (Part part:s.getPart()) {
                partDto = new PartDto();
                partDto.setId(part.getId());
                partDto.setPartName(part.getPartName());
                partDto.setDescription(part.getDescription());
                partDto.setFileType(part.getFileType());
                partDto.setFilePath(part.getPath());
                if (sectionDto.getParts()==null){
                    sectionDto.setParts(new ArrayList());
                }
                sectionDto.getParts().add(partDto);
            }
            list.add(sectionDto);
        }
       return list;
    }
}
