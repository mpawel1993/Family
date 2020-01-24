package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Child;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.exceptions.BusinessException;
import pl.mazur.pawel.Family.repositories.ChildRepository;
import pl.mazur.pawel.Family.repositories.FamilyRepository;

import java.util.List;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FAMILY_NOT_FOUND_STATEMENT;
import static pl.mazur.pawel.Family.exceptions.Statements.FATHER_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor
public class ChildService {

    FamilyRepository familyRepository;
    ChildRepository childRepository;

    public Child addChild(Long familyId, Child child) {
        if (childRepository.findByPesel(child.getPesel()).isPresent()) {
            throw new BusinessException("Child with provided PESEL exist");
        }
        Family family = familyRepository.findById(familyId)
                .orElseThrow(businessException(FATHER_NOT_FOUND_STATEMENT));
        family.getChilds().add(child);
        familyRepository.save(family);
        return childRepository.findFirstByOrderByIdDesc();
    }

    public Child readChild(Long id) {
        return childRepository.findById(id)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT));
    }

    public List<Child> readChilds(Long familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT))
                .getChilds();
    }

    public Child updateChild(Child child) {
        return childRepository.save(child);
    }

    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }
}
