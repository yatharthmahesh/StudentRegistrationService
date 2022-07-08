package com.example.demo.beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {


    @Query("SELECT s from Student s where s.email = ?1" )
    Optional<Student> checkStudentByEmailId(String email);

    @Query("SELECT s from Student s where s.id = ?1")
    Optional<Student> getStudentByStudentId(Long Id);
}
