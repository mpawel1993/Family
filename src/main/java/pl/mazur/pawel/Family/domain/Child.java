package pl.mazur.pawel.Family.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Child {

    @Id
    @GeneratedValue()
    Long id;

    @Column(nullable = false, unique = true)
    String pesel;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String surName;

    @Column(nullable = false)
    LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ChildSex sex;
}
