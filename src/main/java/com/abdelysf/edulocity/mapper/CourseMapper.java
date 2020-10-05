package com.abdelysf.edulocity.mapper;


import com.abdelysf.edulocity.dto.AddCourseDto;
import com.abdelysf.edulocity.dto.CourseResponseDto;
import com.abdelysf.edulocity.model.Category;
import com.abdelysf.edulocity.model.Course;
import com.abdelysf.edulocity.model.Instructor;
import com.abdelysf.edulocity.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    Course mapDtoToCourse(AddCourseDto addCourseDto);

    @Mapping(target ="sectionCount" ,expression = "java(mapSections(course.getSections()))")
    @Mapping(target ="instructorName",expression = "java(mapInstructorName(course.getInstructor()))")
    @Mapping(target ="category",expression = "java(mapCategoryName(course.getCategory()))")
    @Mapping(target = "courseId", source = "id")
    CourseResponseDto mapCourseToResponseDto(Course course);


    default String mapSections(Collection<Section> sections){
        return String.valueOf( sections.size());
    }
    default String mapInstructorName(Instructor instructor){
        return instructor.getFirstName().concat(" "+instructor.getLastName());
    }

    default String mapCategoryName(Category Category){
        return Category.getCategoryName();
    }
}
