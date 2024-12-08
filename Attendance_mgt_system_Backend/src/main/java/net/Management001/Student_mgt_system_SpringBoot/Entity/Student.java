package net.Management001.Student_mgt_system_SpringBoot.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attending_students_information")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "registration_no", nullable = false, unique = true)
    private String registrationNo;

    @Column(nullable = false)
    private String department;

    @Column(name = "year_of_study", nullable = false)
    private int yearOfStudy;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
