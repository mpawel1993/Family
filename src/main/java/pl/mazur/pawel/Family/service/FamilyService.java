package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.FamilySearchCriteria;
import pl.mazur.pawel.Family.repositories.FamilyRepository;

import java.util.List;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FAMILY_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class FamilyService {

    private final FamilyRepository familyRepository;

    public Family createFamily() {
        return familyRepository.save(new Family());
    }

    public Family readFamily(long id) {
        return familyRepository.findById(id)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT));
    }

    public void deleteFamily(long id) {
        familyRepository.deleteById(id);
    }

    public List<Family> searchFamilies(@NonNull FamilySearchCriteria criteria) {
        return familyRepository.searchFamilies(criteria.getFatherFirstName(),
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
                criteria.getChildBirthDay());
    }
}
