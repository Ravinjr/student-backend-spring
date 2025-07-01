package edu.icet.util.mappers;

import edu.icet.dto.CourseDTO;
import edu.icet.dto.CourseSaveRequestDTO;
import edu.icet.dto.requestdto.CourseUpdateRequestDTO;
import edu.icet.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course courseDtoToEntity(CourseSaveRequestDTO courseSaveRequestDTO);
    Course courseUpdateRequestToEntity(CourseUpdateRequestDTO courseUpdateRequestDTO);
    CourseDTO courseEntityToDto(Course course);
    List<CourseDTO> couseEntityListToDtoList(List<Course> courseList);
}
