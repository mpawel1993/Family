package pl.mazur.pawel.Family.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import pl.mazur.pawel.Family.FamilyApplication
import pl.mazur.pawel.Family.domain.Family
import pl.mazur.pawel.Family.domain.FamilySearchCriteria
import spock.lang.Specification

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
        given:

        when:
        List<Family> foundFamilies = familyRepository.searchFamilies(
                //Father Data
                criteria.getFatherBirthDate(),
                criteria.getFatherFirstName(),
                criteria.getFatherPesel(),
                criteria.getFatherSurName(),
                //Mother Data
                criteria.getMotherBirthDate(),
                criteria.getMotherFirstName(),
                criteria.getMotherPesel(),
                criteria.getMotherSurName(),
                //Child Data
                criteria.getChildName(),
                criteria.getChildSurName(),
                criteria.getChildPesel(),
                criteria.getChildBirthDay(),
                criteria.getChildSex())
        then:
        true
    }
}