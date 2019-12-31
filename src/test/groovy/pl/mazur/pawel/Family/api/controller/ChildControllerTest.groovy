package pl.mazur.pawel.Family.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.mazur.pawel.Family.exceptions.FamilyControllerAdvice
import pl.mazur.pawel.Family.mapper.ChildMapper
import pl.mazur.pawel.Family.service.ChildService
import spock.lang.Specification

import static factory.FamilyDtoFactory.createChildDto
import static factory.FamilyFacotry.createChild
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL

@WebMvcTest(controllers = [ChildController, FamilyControllerAdvice])
class ChildControllerTest extends Specification {

    String url = FAMILY_URL

    @Autowired
    MockMvc mvc

    @SpringBean
    ChildService service = Mock()

    @SpringBean
    ChildMapper mapper = Mappers.getMapper(ChildMapper)

    @Autowired
    ObjectMapper objectMapper

    String asJson(Object obj) {
        objectMapper.writeValueAsString(obj)
    }

    void 'Should addChild add new child'() {
        given:
        def familyId = 123L
        def childDto = createChildDto().toBuilder().id(null).build()
        def child = createChild().toBuilder().id(null).build()
        def createChild = createChild().toBuilder().id(321L).build()
        def createdChildDto = createChildDto().toBuilder().id(321L).build()

        when:
        def result = mvc.perform(post("${url}/${familyId}/child")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(childDto)))
        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(createdChildDto)))

        1 * service.addChild(familyId, child) >> createChild
        0 * _._
    }

    void 'Should readChild return child basing on existing childId'() {
        given:
        def childId = 123L
        def child = createChild()
        def childDto = createChildDto()

        when:
        def result = mvc.perform(get('/family/child/123'))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(childDto)))

        1 * service.readChild(childId) >> child
        0 * _._
    }

    void 'Should updateChild update existing child'() {
        given:
        def childDto = createChildDto().toBuilder().id(321L).build()
        def child = createChild().toBuilder().id(321L).build()
        def updatedChild = createChild().toBuilder().id(321L).firstName('newName').build()
        def updatedChildDto = createChildDto().toBuilder().id(321L).firstName('newName').build()

        when:
        def result = mvc.perform(put("${url}/child")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(childDto)))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().json(asJson(updatedChildDto)))

        1 * service.updateChild(child) >> updatedChild
        0 * _._
    }

    void 'Should deleteChild remove child basing on childId'() {
        given:
        def childId = 123L

        when:
        def result = mvc.perform(delete("/family/child/123"))

        then:
        result.andExpect(status().isNoContent())

        1 * service.deleteChild(childId)
        0 * _._
    }

    void 'Should readChilds return found childs '() {
        given:
        def familyId = 123L
        def foundChilds = [createChild(), createChild().toBuilder().id(987L).build()]
        def foundChildsDto = [createChildDto(), createChildDto().toBuilder().id(987L).build()]

        when:
        def result = mvc.perform(get("/family/123/childs"))

        then:
        result.andExpect(status().isOk()).andExpect(content().json(asJson(foundChildsDto)))

        1 * service.readChilds(familyId) >> foundChilds
    }
}
