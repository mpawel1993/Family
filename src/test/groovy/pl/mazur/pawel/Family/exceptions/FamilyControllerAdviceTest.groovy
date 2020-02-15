package pl.mazur.pawel.Family.exceptions

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.mazur.pawel.Family.api.controller.FamilyController
import pl.mazur.pawel.Family.mapper.FamilyMapper
import pl.mazur.pawel.Family.repositories.FamilyRepository
import pl.mazur.pawel.Family.service.FamilyService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [FamilyControllerAdvice, FamilyController])
class FamilyControllerAdviceTest extends Specification {

    @Autowired
    MockMvc mvc

    @SpringBean
    FamilyService service = Mock();
    @SpringBean
    FamilyMapper mapper = Mock();
    @SpringBean
    FamilyRepository familyRepository = Mock();

    def "Should catch exception when thrown"() {
        when:
        def result = mvc.perform(get('/family/create'))
        then:
        result.andExpect(status().is4xxClientError())
        1 * service.createFamily() >> { throw new Exception("exception") }
    }

    def "Should catch runtime_exception when thrown"() {
        when:
        def result = mvc.perform(get('/family/create'))
        then:
        result.andExpect(status().is4xxClientError())
        1 * service.createFamily() >> { throw new RuntimeException("runtime") }
    }
}
