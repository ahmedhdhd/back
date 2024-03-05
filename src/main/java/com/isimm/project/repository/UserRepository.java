package com.isimm.project.repository;
import com.isimm.project.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
User save(User user);
User findByUsername(String username);
}
