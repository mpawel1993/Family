package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.FamilySearchCriteria;
import pl.mazur.pawel.Family.repositories.FamilyRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FAMILY_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class FamilyService {

    private final FamilyRepository familyRepository;

    public Family createFamily() {
        return familyRepository.save(new Family());
    }

    public Family readFamily(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(businessException(FAMILY_NOT_FOUND_STATEMENT));
    }

    public void deleteFamily(Long id) {
        familyRepository.deleteById(id);
    }

    public List<Family> searchFamilies(FamilySearchCriteria criteria) {
        var ff = Stream.of(criteria).anyMatch(Objects::nonNull);

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
                criteria.getChildBirthDay())
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
