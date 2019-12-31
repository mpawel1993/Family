package factory

import pl.mazur.pawel.Family.domain.*

import java.time.LocalDate

class FamilyFacotry {

    static Father createFather() {
        Father.builder()
                .id(1L)
                .birthDate(LocalDate.parse('1965-10-01'))
                .firstName('Roman')
                .pesel('5913203098')
                .surName('Nowak')
                .build()
    }

    static Mother createMother() {
        Mother.builder()
                .id(1L)
                .birthDate(LocalDate.parse('1965-10-01'))
                .firstName('Anna')
                .pesel('6510013098')
                .surName('Nowak')
                .build()
    }

    static Child createChild() {
        Child.builder()
                .id(1L)
                .pesel('010315987')
                .firstName('Wojtek')
                .surName('Nowak')
                .birthDate(LocalDate.parse('2001-03-15'))
                .sex(ChildSex.MALE)
                .build()
    }

    static FamilySearchCriteria createFamilySearchCriteria() {
        FamilySearchCriteria.builder()
                .fatherFirstName('Roman')
                .fatherSurName('Nowak')
                .childName('Zosia')
                .build()
    }

    static Family createFamily() {
        Family.builder()
                .id(1L)
                .father(Father.builder().id(1L).build())
                .mother(Mother.builder().id(1L).build())
                .build()
    }

    static List<Family> createFamilyList() {
        [createFamily().toBuilder().id(2L).build(),
         createFamily().toBuilder().id(3L).build()]
    }
}
