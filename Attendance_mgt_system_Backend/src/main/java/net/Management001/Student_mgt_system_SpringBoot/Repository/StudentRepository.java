package net.Management001.Student_mgt_system_SpringBoot.Repository;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

}
