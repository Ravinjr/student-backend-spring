package edu.icet.service.impl;

import edu.icet.dto.CourseDTO;
import edu.icet.dto.CourseSaveRequestDTO;
import edu.icet.dto.requestdto.CourseUpdateRequestDTO;
import edu.icet.entity.Course;
import edu.icet.exception.NotFoundException;
import edu.icet.repository.CourseRepository;
import edu.icet.service.CourseService;
import edu.icet.util.mappers.CourseMapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceIMPL implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public CourseServiceIMPL(CourseMapper courseMapper, CourseRepository courseRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getCourses(){
        ArrayList<CourseDTO> courseList = new ArrayList<>();
//        courseList.add(new CourseDTO(
//                "Java Master",
//                "7000",
//                127,"12 Months"
//        ));
//        courseList.add(new CourseDTO(
//                "Python Master",
//                "7000",
//                124,"12 Months"
//        ));
        return courseList;
    }

    @Override
    public CourseDTO addCourse(CourseSaveRequestDTO courseSaveRequestDTO) {
        Course course = courseMapper.courseDtoToEntity(courseSaveRequestDTO);
//        courseRepository.save(course);
//        CourseDTO courseDTO = courseMapper.courseEntityToDto(course);
//        return courseDTO;
        if(!courseRepository.existsCourseByCode(courseSaveRequestDTO.getCode())){
            courseRepository.save(course);
            CourseDTO courseDTO = courseMapper.courseEntityToDto(course);
            return courseDTO;
        }else{
            throw new EntityExistsException("The given course already exist.");
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        if(courseList != null){
            List<CourseDTO> courseDTOList = courseMapper.couseEntityListToDtoList(courseList);
            return courseDTOList;
        }else {
            throw new EntityExistsException("There are no courses in the database.");
        }
    }

    @Override
    public CourseDTO updateCourse(CourseUpdateRequestDTO courseUpdateRequestDTO, Long courseId) {
        if(courseRepository.existsById(courseId)){
            Course course1 = courseRepository.getReferenceById(courseUpdateRequestDTO.getCourseId());
            course1.setName(courseUpdateRequestDTO.getName());
            course1.setCode(courseUpdateRequestDTO.getCode());
            course1.setFee(courseUpdateRequestDTO.getFee());
            course1.setDuration(courseUpdateRequestDTO.getDuration());
            courseRepository.save(course1);
            return courseMapper.courseEntityToDto(course1);
        }else{
            throw new NotFoundException("The given course not in the database");
        }
    }

    @Override
    public String deleteCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()){
            courseRepository.deleteById(courseId);
            return "Course deleted successfully";
        }
        throw new NotFoundException("The given course not in the database");
    }
}
