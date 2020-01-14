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

import static factory.FamilyDtoFactory.createCriteriaDto
import static factory.FamilyFacotry.createFamily
import static factory.FamilyFacotry.createFamilyList
import static groovy.json.JsonOutput.toJson
import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL

@WebMvcTest(controllers = [FamilyController, FamilyControllerAdvice])
class FamilyControllerTest extends Specification {

    def url = "${FAMILY_URL}"
    def anyFamily = createFamily()

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @SpringBean
    FamilyService service = Mock()

    @SpringBean
    FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class)

    void 'Should createFamily create new family'() {
        given:
        def createdFamily = anyFamily.setId(123L)
        String link = "http://localhost/family/$createdFamily.id"

        when:
        def response = mvc.perform(get("$url/create"))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.id', is(createdFamily.id as int)))
                .andExpect(jsonPath('$.fatherDto.id', is(createdFamily.father.id as int)))
                .andExpect(jsonPath('$.motherDto.id', is(createdFamily.mother.id as int)))
                .andExpect(jsonPath('$._links.familyId.href', is(link)))

        1 * service.createFamily() >> createdFamily
        0 * _._
    }

    void 'Should readFamily return family basing on id'() {
        given:
        def familyId = 1L
        def foundFamily = anyFamily
        String link = "http://localhost/family/$foundFamily.id"

        when:
        def response = mvc.perform(get("$url/$familyId"))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.id', is(foundFamily.id as int)))
                .andExpect(jsonPath('$.fatherDto.id', is(foundFamily.father.id as int)))
                .andExpect(jsonPath('$.motherDto.id', is(foundFamily.mother.id as int)))
                .andExpect(jsonPath('$._links.familyId.href', is(link)))

        1 * service.readFamily(familyId) >> foundFamily
        0 * _._
    }

    void 'Should deleteFamily remove family'() {
        given:
        long existingFamilyId = 1L

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
        def criteria = familyMapper.map(criteriaDto)
        def foundFamilies = createFamilyList()

        when:
        def response = mvc.perform(get("$url/search").contentType(MediaType.APPLICATION_JSON)
                .content(toJson(criteriaDto)))
        then:
        response.andExpect(status().isMultiStatus())
                .andExpect(jsonPath('$.[0].id', is(2 as int)))
                .andExpect(jsonPath('$.[1].id', is(3 as int)))

        1 * service.searchFamilies(criteria) >> foundFamilies
        0 * _._
    }
}