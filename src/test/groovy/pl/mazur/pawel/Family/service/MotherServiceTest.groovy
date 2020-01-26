package pl.mazur.pawel.Family.service

import pl.mazur.pawel.Family.exceptions.BusinessException
import pl.mazur.pawel.Family.repositories.FamilyRepository
import pl.mazur.pawel.Family.repositories.MotherRepository
import spock.lang.Specification

import static factory.FamilyFacotry.createFamily
import static factory.FamilyFacotry.createMother

class MotherServiceTest extends Specification {

    MotherRepository motherRepository = Mock()
    FamilyRepository familyRepository = Mock()
    def service = MotherService.of(familyRepository, motherRepository)

    void 'Should addMother create new mother'() {
        given:
        def familyId = 123L
        def newMother = createMother().toBuilder().id(null).build()
        def foundFamily = createFamily().toBuilder().mother(null).father(null).build()
        def savedFamily = createFamily().toBuilder().father(null).mother(createMother()).build()

        when:
        def result = service.addMother(familyId, newMother)

        then:
        result == savedFamily.mother

        1 * motherRepository.findByPesel(newMother.pesel) >> Optional.empty()
        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        1 * familyRepository.save(_) >> savedFamily
        0 * _._
    }

    void 'Should addMother throw mother have unexpected motherId'() {
        given:
        def familyId = 123L
        def newMother = createMother()
        def foundFamily = createFamily().toBuilder().father(null).build()

        when:
        service.addMother(familyId, newMother)

        then:
        thrown(BusinessException)

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        1 * motherRepository.findByPesel(newMother.pesel) >> Optional.empty()
        0 * _._
    }

    void 'Should addMother should throw mother already exist exception'() {
        given:
        def familyId = 123L
        def newMother = createMother().toBuilder().id(null).build()
        def foundFamily = createFamily().toBuilder().build()

        when:
        def result = service.addMother(familyId, newMother)

        then:
        thrown(BusinessException)

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        1 * motherRepository.findByPesel(newMother.pesel) >> Optional.empty()
        0 * _._
    }

    void 'Should readMother return read mother'() {
        given:
        def fatherId = 123L
        def foundFather = createMother()

        when:
        def result = service.readMother(fatherId)

        then:
        result == foundFather

        1 * motherRepository.findById(fatherId) >> Optional.of(foundFather)
        0 * _._
    }

    void 'Should updateMother update mother'() {
        given:
        def updatingFather = createMother()
        def updatedFather = createMother().toBuilder().firstName('newName').build()

        when:
        def result = service.updateMother(updatingFather)

        then:
        result == updatedFather

        1 * motherRepository.save(updatingFather) >> updatedFather
        0 * _._
    }

    void 'Should deleteMother delete mother'() {
        given:
        def fatherId = 123L

        when:
        service.deleteMother(fatherId)

        then:
        1 * motherRepository.deleteById(fatherId)
        0 * _._
    }
}
