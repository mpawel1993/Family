package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Child;
import pl.mazur.pawel.Family.exceptions.BusinessException;
import pl.mazur.pawel.Family.repositories.ChildRepository;
import pl.mazur.pawel.Family.repositories.FamilyRepository;

import java.util.List;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FAMILY_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class ChildService {

    private final ChildRepository childRepository;
    private final FamilyRepository familyRepository;

    public Child addChild(Long familyId, Child child) {
        if (childRepository.findByPesel(child.getPesel()).isPresent()) {
            throw new BusinessException("Child with provided PESEL exist");
        }
        child.setFamily(familyRepository
                .findById(familyId)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT)));
        return childRepository.save(child);
    }

    public Child readChild(Long id) {
        return childRepository.findById(id)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT));
    }

    public List<Child> readChilds(Long familyId) {
        return childRepository.findAllByFamily_Id(familyId);
    }

    public Child updateChild(Child child) {
        return childRepository.save(child);
    }

    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }
}
