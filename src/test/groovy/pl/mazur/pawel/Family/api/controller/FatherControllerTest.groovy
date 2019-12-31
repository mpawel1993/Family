package pl.mazur.pawel.Family.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.mazur.pawel.Family.exceptions.FamilyControllerAdvice
import pl.mazur.pawel.Family.mapper.FatherMapper
import pl.mazur.pawel.Family.service.FatherService
import spock.lang.Specification

import static factory.FamilyDtoFactory.createFatherDto
import static factory.FamilyFacotry.createFather
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL

@WebMvcTest(controllers = [FatherController, FamilyControllerAdvice])
class FatherControllerTest extends Specification {

    String url = FAMILY_URL

    @Autowired
    MockMvc mvc

    @SpringBean
    FatherService service = Mock()

    @SpringBean
    FatherMapper mapper = Mappers.getMapper(FatherMapper)

    @Autowired
    ObjectMapper objectMapper

    String asJson(Object obj) {
        objectMapper.writeValueAsString(obj)
    }

    void 'Should addFather add new father'() {
        given:
        def familyId = 123L
        def fatherDto = createFatherDto().toBuilder().id(null).build()
        def father = createFather().toBuilder().id(null).build()
        def createdFather = createFather().toBuilder().id(321L).build()
        def createdFatherDto = createFatherDto().toBuilder().id(321L).build()

        when:
        def result = mvc.perform(post("${url}/${familyId}/father")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(fatherDto)))
        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(createdFatherDto)))

        1 * service.addFather(familyId, father) >> createdFather
        0 * _._
    }

    void 'Should readFather return father basing on existing fatherId'() {
        given:
        def fatherId = 123L
        def father = createFather()
        def fatherDto = createFatherDto()

        when:
        def result = mvc.perform(get('/family/father/123'))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(fatherDto)))

        1 * service.readFather(fatherId) >> father
        0 * _._
    }

    void 'Should updateFather update existing father'() {
        given:
        def fatherDto = createFatherDto().toBuilder().id(321L).build()
        def father = createFather().toBuilder().id(321L).build()
        def updatedFather = createFather().toBuilder().id(321L).firstName('newName').build()
        def updatedFatherDto = createFatherDto().toBuilder().id(321L).firstName('newName').build()

        when:
        def result = mvc.perform(put("${url}/father")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(fatherDto)))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(updatedFatherDto)))

        1 * service.updateFather(father) >> updatedFather
        0 * _._
    }

    def "Should deleteFather remove father basing on fatherId"() {
        given:
        def fatherId = 123L

        when:
        def result = mvc.perform(delete("/family/123/father"))

        then:
        result.andExpect(status().isNoContent())

        1 * service.deleteFather(fatherId)
        0 * _._
    }
}
