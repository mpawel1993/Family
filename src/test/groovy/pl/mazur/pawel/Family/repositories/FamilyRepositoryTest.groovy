package pl.mazur.pawel.Family.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import pl.mazur.pawel.Family.FamilyApplication
import pl.mazur.pawel.Family.domain.FamilySearchCriteria
import spock.lang.Specification

@ActiveProfiles('dev')
@Sql("/tests_data/testsDB.sql")
@DataJpaTest
@ContextConfiguration(classes = FamilyApplication.class)
class FamilyRepositoryTest extends Specification {

    @Autowired
    FamilyRepository familyRepository

    FamilySearchCriteria criteria = FamilySearchCriteria.builder()
            .fatherFirstName(null)
            .fatherPesel(null)
            .fatherSurName(null)
            .fatherBirthDate(null)
            .motherBirthDate(null)
            .motherFirstName(null)
            .motherPesel(null)
            .motherSurName(null)
            .childName(null)
            .childSurName(null)
            .childPesel(null)
            .childBirthDay(null)
            .childSex(null)
            .build()

    def "Should searchFamily find all families passing to search criteria"() {
        when:
        def foundFamilies = familyRepository.searchFamilies(
                //Father Criteria
                criteria.fatherFirstName,
                criteria.fatherSurName,
                criteria.fatherPesel,
                criteria.fatherBirthDate,
                //Mother criteria
                criteria.motherFirstName,
                criteria.motherSurName,
                criteria.fatherPesel,
                criteria.motherBirthDate,
                //Child criteria
                criteria.childName,
                criteria.childSurName,
                criteria.fatherPesel,
                criteria.childSex,
                criteria.childBirthDay
        )

        then:
        println foundFamilies
    }
}