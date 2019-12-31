package pl.mazur.pawel.Family.service

import pl.mazur.pawel.Family.exceptions.BusinessException
import pl.mazur.pawel.Family.repositories.FamilyRepository
import spock.lang.Specification

import static factory.FamilyFacotry.createFamily
import static factory.FamilyFacotry.createFamilySearchCriteria

class FamilyServiceTest extends Specification {

    FamilyRepository repository = Mock()
    FamilyService service = new FamilyService(repository)

    void 'Should createFamily create new family'() {
        given:
        def createdFamily = createFamily()

        when:
        def result = service.createFamily()

        then:
        result == createdFamily

        1 * repository.save(_) >> createdFamily
    }

    void 'Should readFamily find family basing on familyId'() {
        given:
        def familyId = 123L
        def foundFamily = createFamily()

        when:
        def result = service.readFamily(familyId)

        then:
        result == foundFamily

        1 * repository.findById(familyId) >> Optional.of(foundFamily)
    }

    void 'Should readFamily throw exception when family not found'() {
        given:
        def familyId = 123L

        when:
        service.readFamily(familyId)

        then:
        thrown(BusinessException)

        1 * repository.findById(familyId) >> Optional.empty()
    }

    void 'DeleteFamily'() {
        given:
        def familyId = 123L

        when:
        service.deleteFamily(familyId)

        then:
        1 * repository.deleteById(familyId)
    }

    void 'Should searchFamilies return found families basing on search criteria'() {
        given:
        def criteria = createFamilySearchCriteria()
        def foundFamilies = [createFamily(), createFamily().toBuilder().id(987L).build()]

        when:
        service.searchFamilies(criteria)

        then:
        repository.searchFamilies(criteria.getFatherFirstName(),
                criteria.getFatherSurName(),
                criteria.getFatherPesel(),
                criteria.getFatherBirthDate(),
                criteria.getMotherFirstName(),
                criteria.getMotherSurName(),
                criteria.getMotherPesel(),
                criteria.getMotherBirthDate(),
                criteria.getChildName(),
                criteria.getChildSurName(),
                criteria.getChildPesel(),
                criteria.getChildSex(),
                criteria.getChildBirthDay()) >> foundFamilies
    }
}
