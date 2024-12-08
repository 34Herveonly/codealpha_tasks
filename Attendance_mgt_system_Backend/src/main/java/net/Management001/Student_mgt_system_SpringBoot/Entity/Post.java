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

@Table(name = "table_of_posts") // Use consistent naming for tables
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false) // Ensure not-null if required
    private String fullName;

    @Column(nullable = false) // Title should not be null
    private String title;

    @Column(nullable = false) // Content should not be null
    private String content;
}
