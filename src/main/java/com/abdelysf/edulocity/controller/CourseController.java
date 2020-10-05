package com.abdelysf.edulocity.controller;

import com.abdelysf.edulocity.dto.AddCourseDto;
import com.abdelysf.edulocity.dto.CourseResponseDto;
import com.abdelysf.edulocity.mapper.CourseMapper;
import com.abdelysf.edulocity.model.Course;
import com.abdelysf.edulocity.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@Slf4j
public class CourseController {


//    @Value("${dir.images}")
//    private String imageDir;
//    @Value("${dir.videos}")
//    private String videoDir;

    private  final CourseService courseService;
    private final  CourseMapper courseMapper;


    @PostMapping("/create")
    public ResponseEntity createCourse(@ModelAttribute AddCourseDto addCourseDto){

        Course course = courseMapper.mapDtoToCourse(addCourseDto);
        courseService.saveCourse(course,addCourseDto.getImage());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> createCourse(@PathVariable int id){
        CourseResponseDto courseResponseDto = courseMapper.mapCourseToResponseDto(courseService.getCourse(id));

        return new ResponseEntity<CourseResponseDto>(courseResponseDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
        List<Course> allCourses = courseService.getAllCourses();
        List<CourseResponseDto> courseResponseDtoList = allCourses
                                        .stream()
                                        .map(courseMapper::mapCourseToResponseDto)
                                        .collect(Collectors.toList());
        return new ResponseEntity<>(courseResponseDtoList,HttpStatus.OK);
    }


}
