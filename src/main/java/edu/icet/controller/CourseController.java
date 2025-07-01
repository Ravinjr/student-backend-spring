package edu.icet.controller;

import edu.icet.dto.CourseDTO;
import edu.icet.dto.CourseSaveRequestDTO;
import edu.icet.dto.requestdto.CourseUpdateRequestDTO;
import edu.icet.service.CourseService;
import edu.icet.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "/courses-list")
    public List<CourseDTO> getCourses(){

        List<CourseDTO> courses = courseService.getCourses();
        return courses;
    }
    @PostMapping(path = {"/add-course"})
    public ResponseEntity<StandardResponse> addCourse(@RequestBody CourseSaveRequestDTO courseSaveRequestDTO){
        CourseDTO courseDTO = courseService.addCourse(courseSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Created",courseDTO),
                HttpStatus.CREATED
        );
    }
    @GetMapping(path = {"/get-courses"})
    public ResponseEntity<StandardResponse> getAllCourses(){
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",courses),
                HttpStatus.OK
        );
    }
    @PutMapping(path = "/update-course/{courseId}")
    public ResponseEntity<StandardResponse> updateCourse(@RequestBody CourseUpdateRequestDTO courseUpdateRequestDTO, @PathVariable Long courseId){
        CourseDTO courseDTO = courseService.updateCourse(courseUpdateRequestDTO,courseId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Updated",courseDTO),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping(path = {"/delete-course/{courseId}"})
    public ResponseEntity<StandardResponse> deleteUser(@PathVariable Long courseId){
        String deleted = courseService.deleteCourse(courseId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Deleted",deleted),
                HttpStatus.OK
        );
    }

}
