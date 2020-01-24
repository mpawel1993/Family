package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.Father;
import pl.mazur.pawel.Family.exceptions.BusinessException;
import pl.mazur.pawel.Family.repositories.FamilyRepository;
import pl.mazur.pawel.Family.repositories.FatherRepository;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FATHER_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class FatherService {

    private final FatherRepository fatherRepository;
    private final FamilyRepository familyRepository;

    public Father addFather(Long familyId, Father father) {
        if (fatherRepository.findByPesel(father.getPesel()).isPresent()) {
            throw new BusinessException("Father with provided PESEL already exist");
        }

        Family family = familyRepository.findById(familyId)
                .orElseThrow(businessException(FATHER_NOT_FOUND_STATEMENT))
                .checkIsFatherExist();
        father.checkIsFatherHaveUnexpectedId();
        family.setFather(father);
        return familyRepository.save(family).getFather();
    }

    public Father updateFather(Father father) {
        return fatherRepository.save(father);
    }

    public Father readFather(long id) {
        return fatherRepository.findById(id)
                .orElseThrow(businessException(FATHER_NOT_FOUND_STATEMENT));
    }

    public void deleteFather(Long id) {
        fatherRepository.deleteById(id);
    }
}