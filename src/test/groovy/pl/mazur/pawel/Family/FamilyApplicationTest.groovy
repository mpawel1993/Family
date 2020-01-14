package pl.mazur.pawel.Family

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles('dev')
@SpringBootTest
class FamilyApplicationTest extends Specification {

    def "Should run whole spring container"() {
        when: true
        then: true
    }
}
