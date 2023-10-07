package dev.prakash.productservicettsevening.inheritanceexample.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JTUserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findUserById(long id);
}
