package edu.icet.repository.impl;

import edu.icet.repository.StudentNativeRepository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
@Repository
public class StudentNativeRepositoryImpl implements StudentNativeRepository {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentNativeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Boolean deleteStudent(Long studentID){

//        HashMap params = new HashMap();
//        params.put("id", studentID);

        return namedParameterJdbcTemplate.update(
                "DELETE FROM STUDENT WHRE ID=:studentID",
                Collections.singletonMap("id", studentID)) > 0;

//        if(updateStatus > 0 ){
//            return true;
//        }
//        return false;
    }
}
