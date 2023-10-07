package dev.prakash.productservicettsevening.inheritanceexample.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TBCUserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findUserById(long id);
}