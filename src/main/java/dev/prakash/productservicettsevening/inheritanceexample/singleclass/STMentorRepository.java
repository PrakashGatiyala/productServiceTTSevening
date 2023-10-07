package dev.prakash.productservicettsevening.inheritanceexample.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface STMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(long id);
}