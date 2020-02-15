package pl.mazur.pawel.Family.repositories

import pl.mazur.pawel.Family.domain.FamilySearchCriteria

class FamilySearchCriteriaTest {

    static FamilySearchCriteria createFamilyCriteria() {
        FamilySearchCriteria.builder()
                .fatherFirstName('Rom')
                .fatherSurName(null)
                .fatherPesel(null)
                .fatherBirthDate(null)
                .build()
    }

    static FamilySearchCriteria createSecondFamilyCriteria() {
        FamilySearchCriteria.builder().build()
    }
}
