package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.Mother;
import pl.mazur.pawel.Family.exceptions.BusinessException;
import pl.mazur.pawel.Family.repositories.FamilyRepository;
import pl.mazur.pawel.Family.repositories.MotherRepository;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.MOTHER_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class MotherService {

    private final FamilyRepository familyRepository;
    private final MotherRepository motherRepository;

    public Mother addMother(Long familyId, Mother mother) {
        if (motherRepository.findByPesel(mother.getPesel()).isPresent()) {
            throw new BusinessException("Child with provided PESEL already exist");
        }

        Family family = familyRepository.findById(familyId)
                .orElseThrow(businessException(MOTHER_NOT_FOUND_STATEMENT))
                .checkIsMotherExist();
        mother.checkIsMotherHaveUnexpectedId();
        family.setMother(mother);
        return familyRepository.save(family).getMother();
    }

    public Mother updateMother(Mother mother) {
        return motherRepository.save(mother);
    }

    public Mother readMother(Long id) {
        return motherRepository.findById(id)
                .orElseThrow(businessException(MOTHER_NOT_FOUND_STATEMENT));
    }

    public void deleteMother(Long id) {
        motherRepository.deleteById(id);
    }
}
