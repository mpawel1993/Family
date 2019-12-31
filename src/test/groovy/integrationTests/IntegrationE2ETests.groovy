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
class IntegrationE2ETests extends Specification {

    @LocalServerPort
    int port;

    def local = 'http://localhost:'

    void 'test'() {
        when:
        RestTemplate restTemplate = new RestTemplate()

        def fam = restTemplate.getForEntity("${local}${port}/family/create", FamilyDto.class)
        def createdFamily = restTemplate.getForEntity("${local}${port}/family/${fam.body.id}", FamilyDto.class)

        then:
        createdFamily.statusCode.value() == 200
    }
}
