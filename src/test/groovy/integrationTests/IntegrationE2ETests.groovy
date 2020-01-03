package integrationTests

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import pl.mazur.pawel.Family.FamilyApplication
import pl.mazur.pawel.Family.api.dto.ChildDto
import pl.mazur.pawel.Family.api.dto.FamilyDto
import pl.mazur.pawel.Family.api.dto.FatherDto
import pl.mazur.pawel.Family.api.dto.MotherDto
import spock.lang.Specification

import static factory.FamilyFacotry.*

@ActiveProfiles('dev')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FamilyApplication.class)
class IntegrationE2ETests extends Specification {

    @LocalServerPort
    int port;

    def local = 'https://localhost:'

    void 'adding flow '() {
        given:
        def restTemplate = new RestTemplate()
        def father = createFather().toBuilder().id(null).build()
        def mother = createMother().toBuilder().id(null).build()
        def child = createChild().toBuilder().id(null).build()
        def secondChild = createChild().toBuilder().id(null).pesel("75363213").build()

        def editChild = createChild().toBuilder().id(null).pesel("57834523").build()

        when:
        //Adding Flow
        def createdFamily = restTemplate.getForEntity("${local}${port}/family/create", FamilyDto.class)
        def addedFather = restTemplate.postForEntity("${local}${port}/family/${createdFamily.body.id}/father", father, FatherDto.class)
        def addedMother = restTemplate.postForEntity("${local}${port}/family/${createdFamily.body.id}/mother", mother, MotherDto.class)
        def addedChild = restTemplate.postForEntity("${local}${port}/family/${createdFamily.body.id}/child", child, ChildDto.class)
        def addedSecondChild = restTemplate.postForEntity("${local}${port}/family/${createdFamily.body.id}/child", secondChild, ChildDto.class)

        //Editing Flow
        def editedChild = restTemplate.postForEntity("${local}${port}/family/${createdFamily.body.id}/child", editChild, ChildDto.class)
        //Deleting Flow
        def deleteChild = restTemplate.delete("${local}${port}/family/child/${editedChild.body.id}")
        //Getting Flow
        def actualFamily = restTemplate.getForEntity("${local}${port}/family/${createdFamily.body.id}", FamilyDto.class)

        then:
        createdFamily.statusCode.value() == 200
        addedFather.statusCode.value() == 200
        addedMother.statusCode.value() == 200
        addedChild.statusCode.value() == 200
        addedSecondChild.statusCode.value() == 200
        editedChild.statusCode.value() == 200
    }
}
