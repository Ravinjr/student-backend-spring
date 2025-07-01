package edu.icet.controller;

import edu.icet.common.AuditTime;
import edu.icet.dto.StudentDTO;
import edu.icet.dto.responsedto.deleteResponse;
import edu.icet.dto.responsedto.DogResponse;
import edu.icet.entity.Student;
import edu.icet.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping(path = "/create-student")
    @AuditTime
    public Student createStudent(@RequestBody StudentDTO studentDTO){
      return studentService.createStudent(studentDTO);
    }

    @GetMapping("/student-list")
    @AuditTime
    public List<StudentDTO> loadStudents(){
//        Thread.sleep(5000);
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete-student/{studentID}")
    public deleteResponse deleteStudent(@PathVariable Long studentID){
        return studentService.deleteStudent(studentID) ?
                new deleteResponse("Student deleted Id"):
                new deleteResponse(" Student not found");

//                new edu.icet.dto.Response(String.format("Removed Id(%s"),studentID):
//                new edu.icet.dto.Response(String.format("No student Id(%s"),studentID);
    }

    @GetMapping("/details")
    @AuditTime
    public ResponseEntity<DogResponse> getInformation(){
//        Thread.sleep(5000);
        return studentService.getInformation();
    }
}
