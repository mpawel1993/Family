package pl.mazur.pawel.Family.repositories

import pl.mazur.pawel.Family.domain.*

import java.time.LocalDate

class RepositoryData {

    static def createFirstFamily() {
        def father_1 = Father.builder()
                .firstName('Roman')
                .surName('Romanowicz')
                .birthDate(LocalDate.of(1958, 01, 01))
                .pesel('58010195')
                .build()
        def mother_1 = Mother.builder()
                .firstName('Anna')
                .surName('Romanowicz')
                .birthDate(LocalDate.of(1967, 05, 05))
                .pesel('5891828382')
                .build()
        def childs_1 = [Child.builder()
                                .firstName('John')
                                .surName('Romanowicz')
                                .birthDate(LocalDate.of(1992, 01, 06))
                                .pesel('199201067478')
                                .sex(ChildSex.MALE)
                                .build(),
                        Child.builder()
                                .firstName('Zosia')
                                .surName('Romanowicz')
                                .birthDate(LocalDate.of(2001, 05, 01))
                                .pesel('200105018674')
                                .sex(ChildSex.FEMALE)
                                .build()]

        def family_1 = Family.builder()
                .father(father_1)
                .mother(mother_1)
                .childs(childs_1)
                .build()
        family_1
    }

    static createSecondFamily() {
        def father_2 = Father.builder()
                .firstName('John')
                .surName('Snow')
                .birthDate(LocalDate.of(1987, 8, 12))
                .pesel('198708129857')
                .build()
        def mother_2 = Mother.builder()
                .firstName('Mariola')
                .surName('Snow')
                .birthDate(LocalDate.of(1990, 11, 25))
                .pesel('1990,11,25')
                .build()
        def childs_2 = [Child.builder()
                                .firstName('Kamil')
                                .surName('Snow')
                                .birthDate(LocalDate.of(2005, 10, 06))
                                .pesel('200510068756')
                                .sex(ChildSex.MALE)
                                .build(),
                        Child.builder()
                                .firstName('Emma')
                                .surName('Snow')
                                .birthDate(LocalDate.of(2005, 03, 14))
                                .pesel('20050314987')
                                .sex(ChildSex.FEMALE)
                                .build()]

        def family_2 = Family.builder()
                .father(father_2)
                .mother(mother_2)
                .childs(childs_2)
                .build()
        family_2
    }
}
