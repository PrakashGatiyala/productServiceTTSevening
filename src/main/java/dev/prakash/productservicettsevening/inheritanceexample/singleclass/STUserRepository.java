package dev.prakash.productservicettsevening.inheritanceexample.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface STUserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findUserById(long id);
}