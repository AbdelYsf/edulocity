package com.abdelysf.edulocity.service;

import com.abdelysf.edulocity.dto.CourseResponseDto;
import com.abdelysf.edulocity.exceptions.EduLocityException;
import com.abdelysf.edulocity.mapper.CourseMapper;
import com.abdelysf.edulocity.model.Course;
import com.abdelysf.edulocity.model.Instructor;
import com.abdelysf.edulocity.model.User;
import com.abdelysf.edulocity.repository.ICourseDAO;
import com.abdelysf.edulocity.repository.IUserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@Service
public class CourseService {

    @Value("${dir.images}")
    private String imageDir;


    @Autowired
    private  IUserDAO userRepository;
    @Autowired
    private ICourseDAO courseRepository;

    public void saveCourse(Course course, MultipartFile file){
        if(!(file.isEmpty())){
            course.setImagePath(imageDir.concat(file.getOriginalFilename()));
            try {
                file.transferTo(new File(imageDir.concat(file.getOriginalFilename())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User instructor =userRepository.findByUserName(currentUserName).orElseThrow(()->new EduLocityException("cannot find the user "));
            course.setInstructor((Instructor) instructor);
            courseRepository.save(course);

        }

    }

    public Course getCourse(int id) {
       return courseRepository.findCourseById(id).orElseThrow(() -> new EduLocityException("cannot find the course with the id " + id));

    }

    public List<Course> getAllCourses() {
      return   courseRepository.findAll();
    }
}
