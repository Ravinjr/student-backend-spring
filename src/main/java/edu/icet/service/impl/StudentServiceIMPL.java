package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.StudentDTO;
import edu.icet.dto.responsedto.DogResponse;
import edu.icet.entity.Student;
import edu.icet.repository.StudentNativeRepository;
import edu.icet.repository.StudentRepository;
import edu.icet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StudentServiceIMPL implements StudentService {

    private final StudentNativeRepository studentNativeRepository;
    private final StudentRepository studentRepository;
    private final ObjectMapper mapper;

    @Autowired
    RestTemplate restTemplate;

    public StudentServiceIMPL(StudentNativeRepository studentNativeRepository, StudentRepository studentRepository, ObjectMapper mapper, RestTemplate restTemplate) {
        this.studentNativeRepository = studentNativeRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public Student createStudent(StudentDTO studentDTO) {
        Student student = mapper.convertValue(studentDTO,Student.class);
//        Student student = new Student();
//        student.setFirstName(studentDTO.getFirstName());
//        student.setLastName(studentDTO.getLastName());
//        student.setContactNumber(studentDTO.getContactNumber());
        return studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        Iterable<Student> students = studentRepository.findAll();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()){
            Student entity = iterator.next();
            StudentDTO studentDTO = mapper.convertValue(entity,StudentDTO.class);
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    public boolean deleteStudent(Long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            student.get();
            studentRepository.deleteById(studentId);
            return true;
        }
        else{
            return false;
        }
//        return studentNativeRepository.deleteStudent(studentId);
    }


   public ResponseEntity<DogResponse> getInformation(){
        ResponseEntity<DogResponse> exchange = this.restTemplate.exchange(
                "https://dog.ceo/api/breeds/image/random",
                HttpMethod.GET, null,
                DogResponse.class
        );
    return exchange;
    }

}
