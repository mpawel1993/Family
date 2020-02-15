package pl.mazur.pawel.Family.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.mazur.pawel.Family.exceptions.FamilyControllerAdvice
import pl.mazur.pawel.Family.mapper.FamilyMapper
import pl.mazur.pawel.Family.service.FamilyService
import spock.lang.Specification

import static factory.FamilyDtoFactory.*
import static factory.FamilyFacotry.createFamily
import static factory.FamilyFacotry.createFamilyList
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL

@WebMvcTest(controllers = [FamilyController, FamilyControllerAdvice])
class FamilyControllerTest extends Specification {

    def url = "${FAMILY_URL}"


    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @SpringBean
    FamilyService service = Mock()

    @SpringBean
    FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class)

    String asJson(def obj) {
        objectMapper.writeValueAsString(obj)
    }

    void 'Should createFamily create new family'() {
        given:
        def createdFamily = createFamily().toBuilder().id(123L).build()
        def createdFamilyDto = createFamilyDto().toBuilder().id(123L).build()

        when:
        def response = mvc.perform(get("$url/create"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().json(asJson(createdFamilyDto)))

        1 * service.createFamily() >> createdFamily
        0 * _._
    }

    void 'Should readFamily return family basing on id'() {
        given:
        def familyId = 1L
        def foundFamily = createFamily()
        def foundFamilyDto = createFamilyDto()

        when:
        def response = mvc.perform(get("$url/$familyId"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().json(asJson(foundFamilyDto)))

        1 * service.readFamily(familyId) >> foundFamily
        0 * _._
    }

    void 'Should deleteFamily remove family'() {
        given:
        def existingFamilyId = 1L

        when:
        def response = mvc.perform(delete("$url/$existingFamilyId"))

        then:
        response.andExpect(status().isNoContent())

        1 * service.deleteFamily(existingFamilyId)
        0 * _._
    }

    void 'Should searchFamily find all families basing on search criteria'() {
        given:
        def criteriaDto = createCriteriaDto()
        def foundFamilies = createFamilyList()
        def foundFamiliesDto = createFamilyDtoList()

        when:
        def response = mvc.perform(get("$url/search").contentType(MediaType.APPLICATION_JSON)
                .content(toJson(criteriaDto)))
        then:
        response.andExpect(status().isMultiStatus())
                .andExpect(content().json(asJson(foundFamiliesDto)))
        1 * service.searchFamilies(_) >> foundFamilies
        0 * _._
    }
}
