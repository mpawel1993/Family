package pl.mazur.pawel.Family.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.mazur.pawel.Family.domain.FamilySearchCriteria
import spock.lang.Specification

import static pl.mazur.pawel.Family.repositories.RepositoryData.createFirstFamily
import static pl.mazur.pawel.Family.repositories.RepositoryData.createSecondFamily

@ActiveProfiles('dev')
@SpringBootTest
class FamilyRepositoryTest extends Specification {

    @Autowired
    FamilyRepository familyRepository

    def "SearchFamilies"() {
        given:
        prepareTestData()
        FamilySearchCriteria crt = FamilySearchCriteria.builder().build()

        when:
        def result = familyRepository.findAll()

        then:
        println result
    }

    private void prepareTestData() {
        familyRepository.save(createFirstFamily())
        familyRepository.save(createSecondFamily())
    }
}
