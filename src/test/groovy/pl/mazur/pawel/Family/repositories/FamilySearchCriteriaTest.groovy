package pl.mazur.pawel.Family.repositories

import pl.mazur.pawel.Family.domain.FamilySearchCriteria

import java.time.LocalDate

class FamilySearchCriteriaTest {

    static FamilySearchCriteria createFamilyCriteria() {
        FamilySearchCriteria.builder()
                .fatherFirstName('Rom')
                .motherFirstName('An')
                .childName('J')
                .build()
    }

    static FamilySearchCriteria createSecondFamilyCriteria() {
        FamilySearchCriteria.builder()
                .fatherFirstName('Joh')
                .motherBirthDate(LocalDate.of(1990, 11, 25))
                .childPesel('20050314987')
                .build()
    }
}
