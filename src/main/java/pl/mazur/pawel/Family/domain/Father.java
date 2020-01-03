package pl.mazur.pawel.Family.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import pl.mazur.pawel.Family.exceptions.BusinessException;

import javax.persistence.*;
import java.time.LocalDate;

import static pl.mazur.pawel.Family.service.FatherService.ALREADY_EXISTING_FATHER_STATEMENT;

@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Father {

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

    @OneToOne
    Family family;

    public void checkIsFatherHaveUnexpectedId() {
        if (this.getId() != null) {
            throw new BusinessException(ALREADY_EXISTING_FATHER_STATEMENT);
        }
    }
}
