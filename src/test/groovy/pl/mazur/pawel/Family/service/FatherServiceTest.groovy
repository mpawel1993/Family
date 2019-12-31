package pl.mazur.pawel.Family.service

import pl.mazur.pawel.Family.exceptions.BusinessException
import pl.mazur.pawel.Family.repositories.FamilyRepository
import pl.mazur.pawel.Family.repositories.FatherRepository
import spock.lang.Specification

import static factory.FamilyFacotry.createFamily
import static factory.FamilyFacotry.createFather

class FatherServiceTest extends Specification {

    FatherRepository fatherRepository = Mock()
    FamilyRepository familyRepository = Mock()
    def service = FatherService.of(fatherRepository, familyRepository)

    void 'Should addFather create new father'() {
        given:
        def familyId = 123L
        def newFather = createFather().toBuilder().id(null).build()
        def createdFather = createFather()
        def foundFamily = createFamily().toBuilder().father(null).build()

        when:
        def result = service.addFather(familyId, newFather)

        then:
        result == createdFather

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        1 * fatherRepository.save(newFather) >> createdFather
        0 * _._
    }

    void 'Should addFather throw father have unexpected fatherId'() {
        given:
        def familyId = 123L
        def newFather = createFather()
        def foundFamily = createFamily().toBuilder().father(null).build()

        when:
        service.addFather(familyId, newFather)

        then:
        thrown(BusinessException)

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        0 * _._
    }

    void 'Should addFather should throw father already exist exception'() {
        given:
        def familyId = 123L
        def newFather = createFather().toBuilder().id(null).build()
        def foundFamily = createFamily().toBuilder().build()

        when:
        def result = service.addFather(familyId, newFather)

        then:
        thrown(BusinessException)

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        0 * _._
    }

    void 'Should readFather return read father'() {
        given:
        def fatherId = 123L
        def foundFather = createFather()

        when:
        def result = service.readFather(fatherId)

        then:
        result == foundFather

        1 * fatherRepository.findById(fatherId) >> Optional.of(foundFather)
        0 * _._
    }

    void 'Should updateFather update father'() {
        given:
        def updatingFather = createFather()
        def updatedFather = createFather().toBuilder().firstName('newName').build()

        when:
        def result = service.updateFather(updatingFather)

        then:
        result == updatedFather

        1 * fatherRepository.save(updatingFather) >> updatedFather
        0 * _._
    }

    void 'Should deleteFather delete Father'() {
        given:
        def fatherId = 123L

        when:
        service.deleteFather(fatherId)

        then:
        1 * fatherRepository.deleteById(fatherId)
        0 * _._
    }
}
