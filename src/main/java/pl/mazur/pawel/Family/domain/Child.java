package pl.mazur.pawel.Family.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Child {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(nullable = false)
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
