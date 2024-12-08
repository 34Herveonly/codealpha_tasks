package net.Management001.Student_mgt_system_SpringBoot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers_information") // Use consistent table naming
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false) // Adjust column name to match conventions
    private String fullName;

    @Column(nullable = false, unique = true) // Email should typically be unique
    private String email;

    @Column(nullable = false) // Ensure phone is not null if required
    private String phone;

    @Column(nullable = false) // Ensure password is not null
    private String password;
}
