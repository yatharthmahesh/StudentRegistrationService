package com.example.demo.beans;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "student_sequence"
    )
    private long id;

    private String name;

    public int getAge() {
        return Period.between(DOB, LocalDate.now()).getYears();
    }

    @Transient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int age;

    private LocalDate DOB;

    private String email;

    public Student(String name, LocalDate date, String email){
        this.name = name;
        this.DOB = date;
        this.email = email;
    }
}
