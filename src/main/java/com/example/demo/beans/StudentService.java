package com.example.demo.beans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student){

        Optional<Student> studentOptional = studentRepository.checkStudentByEmailId(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Already Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> optionalStudent= studentRepository.getStudentByStudentId(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException(String.format("No Student Present With studentId %s", studentId.toString()));
        }
        studentRepository.findById(studentId);
        studentRepository.delete(optionalStudent.get());
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Optional<Student> optionalStudent = studentRepository.getStudentByStudentId(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException(String.format("No Student Present With This studentId %s",studentId));
        }
        Student student= optionalStudent.get();
        if(name != null && name.length() >0 ){
            student.setName(name);
        }
        if(email != null && email.length() >0 ){
            if(studentRepository.checkStudentByEmailId(email).isPresent()) {
                throw new IllegalArgumentException("Student With This Email Id Already Present");
            }
            student.setEmail(email);
        }

    }
}
