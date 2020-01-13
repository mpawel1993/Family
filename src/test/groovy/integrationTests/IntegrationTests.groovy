package integrationTests

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import pl.mazur.pawel.Family.FamilyApplication
import pl.mazur.pawel.Family.api.dto.FamilyDto
import spock.lang.Specification

@ActiveProfiles('dev')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FamilyApplication.class)
class IntegrationTests extends Specification {

    @LocalServerPort
    int port;
    def local = 'http://localhost:'

    void 'Should save and read family'() {
        given:
        def rt = new RestTemplate()

        when:
        def createdFamily = rt.getForEntity("${local}${port}/family/create", FamilyDto.class)
        def readFamily = rt.getForEntity("${local}${port}/family/${createdFamily.body.id}", FamilyDto.class)

        then:
        createdFamily.statusCode.value() == 200
        readFamily.statusCode.value() == 200
    }
}
