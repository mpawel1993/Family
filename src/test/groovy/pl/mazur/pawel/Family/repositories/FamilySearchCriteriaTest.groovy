package pl.mazur.pawel.Family.repositories

import pl.mazur.pawel.Family.domain.FamilySearchCriteria

class FamilySearchCriteriaTest {

    static FamilySearchCriteria createFamilyCriteria() {
        FamilySearchCriteria.builder()
                .fatherFirstName('Roman')
                .build()
    }

    static FamilySearchCriteria createSecondFamilyCriteria() {
        FamilySearchCriteria.builder().build()
    }
}
