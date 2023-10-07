package dev.prakash.productservicettsevening.inheritanceexample.mappedsuperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MSMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(long id);
}