package net.Management001.Student_mgt_system_SpringBoot.Repository;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
