package com.abdelysf.edulocity.service;

import com.abdelysf.edulocity.dto.AddPartRequest;
import com.abdelysf.edulocity.dto.AddSectionRequest;
import com.abdelysf.edulocity.exceptions.CourseNotFoundException;
import com.abdelysf.edulocity.exceptions.SectionNotFoundException;
import com.abdelysf.edulocity.exceptions.UserNotFoundException;
import com.abdelysf.edulocity.model.*;
import com.abdelysf.edulocity.repository.ICourseDAO;
import com.abdelysf.edulocity.repository.IPartDAO;
import com.abdelysf.edulocity.repository.ISectionDAO;
import com.abdelysf.edulocity.repository.IUserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;



@Slf4j
@Service
public class CourseService {

    @Value("${dir.images}")
    private String imageDir;

    @Value("${dir.videos}")
    private String videoDir;


    @Autowired
    private  IUserDAO userRepository;
    @Autowired
    private ICourseDAO courseRepository;
    @Autowired
    private ISectionDAO sectionRepository;
    @Autowired
    private IPartDAO partRepository;

    @Transactional
    public void saveCourse(Course course, MultipartFile file){
        // uploading the image
        if(!(file.isEmpty())){
            course.setImagePath(imageDir.concat(file.getOriginalFilename()));
            try {
                file.transferTo(new File(imageDir.concat(file.getOriginalFilename())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // get the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User instructor =userRepository
                            .findByUserName(currentUserName)
                            .orElseThrow(
                                    ()->new UserNotFoundException(String.format("cannot find the user with username {}",currentUserName))
                            );
            //associate the course with instructor
            course.setInstructor((Instructor) instructor);
            courseRepository.save(course);

        }

    }

    @Transactional(readOnly = true)
    public Course getCourse(int id) {
       return courseRepository
               .findCourseById(id)
               .orElseThrow( ()-> new CourseNotFoundException(
                               String.format("cannot find the course wih id: {} ",id)
                       )
               );

    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
      return   courseRepository.findAll();
    }

    @Transactional
    public void addSectionToCourse(AddSectionRequest addSectionRequest) {
        Course course = courseRepository
                .findById(addSectionRequest.getCourseId())
                .orElseThrow(
                        ()-> new CourseNotFoundException(
                                String.format("cannot find the course wih id: {} ",addSectionRequest.getCourseId())
                        )
                );
        //mapping
        Section section = new Section();
        section.setSectionName(addSectionRequest.getSectionName());
        section.setDescription(addSectionRequest.getDescription());
        section.setCourse(course);
        course.getSections().add(section);

        //saving
        courseRepository.save(course);


    }
    @Transactional
    public void addPartToSection(AddPartRequest addPartRequest) {
        Section section = sectionRepository
                .findById(addPartRequest.getSectionId())
                .orElseThrow(
                        () -> new SectionNotFoundException(
                                String.format("cannot find exception with id {}", addPartRequest.getSectionId())
                        )
                );
            //mapping
        Part part = new Part();
        part.setSection(section);
        part.setPartName(addPartRequest.getPartName());
        part.setDescription(addPartRequest.getDescription());
        part.setFileType(addPartRequest.getFileType());

        if(!(addPartRequest.getFile().isEmpty())){
            part.setPath(videoDir.concat(addPartRequest.getFile().getOriginalFilename()));
        }
        //save
        partRepository.save(part);
        //uploading the associated file
        if(!(addPartRequest.getFile().isEmpty())){
            try {
                addPartRequest.getFile().transferTo(new File(videoDir+addPartRequest.getFile().getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
