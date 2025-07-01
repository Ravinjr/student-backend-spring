package edu.icet.service;

import edu.icet.dto.StudentDTO;
import edu.icet.dto.responsedto.DogResponse;
import edu.icet.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    boolean deleteStudent(Long studentId);

    ResponseEntity<DogResponse> getInformation();
}
