package pl.mazur.pawel.Family.service


import pl.mazur.pawel.Family.repositories.ChildRepository
import pl.mazur.pawel.Family.repositories.FamilyRepository
import spock.lang.Specification

import static factory.FamilyFacotry.createChild
import static factory.FamilyFacotry.createFamily

class ChildServiceTest extends Specification {

    ChildRepository childRepository = Mock()
    FamilyRepository familyRepository = Mock()
    def service = ChildService.of(childRepository, familyRepository)

    void 'Should addChild create new child'() {
        given:
        def familyId = 123L
        def newChild = createChild().toBuilder().id(null).build()
        def createdChild = createChild()
        def foundFamily = createFamily()

        when:
        def result = service.addChild(familyId, newChild)

        then:
        result == createdChild

        1 * familyRepository.findById(familyId) >> Optional.of(foundFamily)
        1 * childRepository.save(newChild) >> createdChild
        1 * childRepository.findByPesel(newChild.pesel) >> Optional.empty()
        0 * _._
    }

    void 'Should readChild return read child'() {
        given:
        def childId = 123L
        def foundChild = createChild()

        when:
        def result = service.readChild(childId)

        then:
        result == foundChild

        1 * childRepository.findById(childId) >> Optional.of(foundChild)
        0 * _._
    }

    void 'Should updateChild update child'() {
        given:
        def updatingChild = createChild()
        def updatedChild = createChild().toBuilder().firstName('newName').build()

        when:
        def result = service.updateChild(updatingChild)

        then:
        result == updatedChild

        1 * childRepository.save(updatingChild) >> updatedChild
        0 * _._
    }

    void 'Should deleteChild delete child'() {
        given:
        def childId = 123L

        when:
        service.deleteChild(childId)

        then:
        1 * childRepository.deleteById(childId)
        0 * _._
    }

    void 'Should readChilds find childs belongs to family'() {
        given:
        def familyId = 123L
        def foundChilds = [createChild(), createChild().toBuilder().id(987L).build()]

        when:
        def result = service.readChilds(familyId)

        then:
        result == foundChilds

        1 * childRepository.findAllByFamily_Id(familyId) >> foundChilds
    }
}
