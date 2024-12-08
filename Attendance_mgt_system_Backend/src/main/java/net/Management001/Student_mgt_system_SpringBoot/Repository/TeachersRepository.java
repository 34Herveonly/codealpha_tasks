package net.Management001.Student_mgt_system_SpringBoot.Repository;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
    Optional<Teachers> findByEmail(String email);
}
