package pl.mazur.pawel.Family.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.mazur.pawel.Family.exceptions.FamilyControllerAdvice
import pl.mazur.pawel.Family.mapper.MotherMapper
import pl.mazur.pawel.Family.service.MotherService
import spock.lang.Specification

import static factory.FamilyDtoFactory.createMotherDto
import static factory.FamilyFacotry.createMother
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL

@WebMvcTest(controllers = [MotherController, FamilyControllerAdvice])
class MotherControllerTest extends Specification {

    String url = FAMILY_URL

    @Autowired
    MockMvc mvc

    @SpringBean
    MotherService service = Mock()

    @SpringBean
    MotherMapper mapper = Mappers.getMapper(MotherMapper)

    @Autowired
    ObjectMapper objectMapper

    String asJson(Object obj) {
        objectMapper.writeValueAsString(obj)
    }

    void 'Should addMother add new mother'() {
        given:
        def familyId = 123L
        def motherDto = createMotherDto().toBuilder().id(null).build()
        def mother = createMother().toBuilder().id(null).build()
        def createdMother = createMother().toBuilder().id(321L).build()
        def createdMotherDto = createMotherDto().toBuilder().id(321L).build()

        when:
        def result = mvc.perform(post("${url}/${familyId}/mother")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(motherDto)))
        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(createdMotherDto)))

        1 * service.addMother(familyId, mother) >> createdMother
        0 * _._
    }

    void 'Should readMother return mother basing on existing motherId'() {
        given:
        def motherId = 123L
        def mother = createMother()
        def motherDto = createMotherDto()

        when:
        def result = mvc.perform(get('/family/mother/123'))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(motherDto)))

        1 * service.readMother(motherId) >> mother
        0 * _._
    }

    void 'Should updateMother update existing mother'() {
        given:
        def motherDto = createMotherDto().toBuilder().id(321L).build()
        def father = createMother().toBuilder().id(321L).build()
        def updatedFather = createMother().toBuilder().id(321L).firstName('newName').build()
        def updatedFatherDto = createMotherDto().toBuilder().id(321L).firstName('newName').build()

        when:
        def result = mvc.perform(put("${url}/mother")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(motherDto)))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(updatedFatherDto)))

        1 * service.updateMother(father) >> updatedFather
        0 * _._
    }

    void 'Should deleteMother remove father basing on fatherId'() {
        given:
        def motherId = 123L

        when:
        def result = mvc.perform(delete("/family/mother/123"))

        then:
        result.andExpect(status().isNoContent())

        1 * service.deleteMother(motherId)
        0 * _._
    }
}
