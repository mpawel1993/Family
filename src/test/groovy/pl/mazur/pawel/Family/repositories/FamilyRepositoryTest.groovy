package pl.mazur.pawel.Family.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Unroll

import static pl.mazur.pawel.Family.repositories.FamilySearchCriteriaTest.createFamilyCriteria
import static pl.mazur.pawel.Family.repositories.FamilySearchCriteriaTest.createSecondFamilyCriteria
import static pl.mazur.pawel.Family.repositories.RepositoryData.createFirstFamily
import static pl.mazur.pawel.Family.repositories.RepositoryData.createSecondFamily

@ActiveProfiles('dev')
@SpringBootTest
class FamilyRepositoryTest extends Specification {

    @Autowired
    FamilyRepository familyRepository

    def setup() {
        familyRepository.deleteAll()
        familyRepository.save(createFirstFamily())
        familyRepository.save(createSecondFamily())
    }

    @Unroll
    def "SearchFamilies"() {
        when:
        def result = familyRepository.searchFamilies(criteria.fatherFirstName,
                criteria.fatherSurName,
                criteria.fatherPesel,
                criteria.fatherBirthDate,
                criteria.motherFirstName,
                criteria.motherSurName,
                criteria.motherPesel,
                criteria.motherBirthDate,
                criteria.childName,
                criteria.childSurName,
                criteria.childPesel,
                criteria.childSex,
                criteria.childBirthDay)
                .stream().distinct().findFirst().get()

        then:
        result.id == expected_family_id

        where:
        criteria                     | expected_family_id
        createFamilyCriteria()       | 1L
        createSecondFamilyCriteria() | 16L

    }
}
