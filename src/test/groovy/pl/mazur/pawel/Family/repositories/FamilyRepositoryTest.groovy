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
        prepareTestData()
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
                criteria.childBirthDay
        )

        then:
        result != expected_result


        where:
        criteria                     | expected_result
        createFamilyCriteria()       | null
        createSecondFamilyCriteria() | null

    }

    private void prepareTestData() {
        familyRepository.deleteAll()
        familyRepository.save(createFirstFamily())
        familyRepository.save(createSecondFamily())
    }
}
