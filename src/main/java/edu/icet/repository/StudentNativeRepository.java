package edu.icet.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentNativeRepository {
    Boolean deleteStudent(Long studentID);
}
