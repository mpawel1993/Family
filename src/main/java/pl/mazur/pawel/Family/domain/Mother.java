package pl.mazur.pawel.Family.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.mazur.pawel.Family.exceptions.BusinessException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static pl.mazur.pawel.Family.exceptions.Statements.ALREADY_EXISTING_MOTHER_STATEMENT;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mother {

    @Id
    @GeneratedValue()
    Long id;

    @Column(nullable = false)
    LocalDate birthDate;

    @Column(nullable = false)
    String firstName;

    @Column(unique = true, nullable = false)
    String pesel;

    @Column(nullable = false)
    String surName;

    public void checkIsMotherHaveUnexpectedId() {
        if (this.getId() != null) {
            throw new BusinessException(ALREADY_EXISTING_MOTHER_STATEMENT);
        }
    }
}
