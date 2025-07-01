package edu.icet.service;

import edu.icet.dto.CourseDTO;
import edu.icet.dto.CourseSaveRequestDTO;
import edu.icet.dto.requestdto.CourseUpdateRequestDTO;

import java.util.List;

public interface CourseService  {
    List<CourseDTO> getCourses();

    CourseDTO addCourse(CourseSaveRequestDTO courseSaveRequestDTO);

    List<CourseDTO> getAllCourses();

    CourseDTO updateCourse(CourseUpdateRequestDTO courseUpdateRequestDTO, Long courseId);

    String deleteCourse(Long courseId);
}
