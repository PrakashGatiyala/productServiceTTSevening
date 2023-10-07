package dev.prakash.productservicettsevening.inheritanceexample.joinedtable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_mentor")
@PrimaryKeyJoinColumn(name = "user_id")

public class Mentor extends User {
    private int numberOfMentees;
    private int numberOfSessions;
}
