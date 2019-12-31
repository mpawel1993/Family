package pl.mazur.pawel.Family.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.Father;
import pl.mazur.pawel.Family.repositories.FamilyRepository;
import pl.mazur.pawel.Family.repositories.FatherRepository;

import static pl.mazur.pawel.Family.exceptions.BusinessException.businessException;
import static pl.mazur.pawel.Family.exceptions.Statements.FATHER_NOT_FOUND_STATEMENT;

@Service
@AllArgsConstructor(staticName = "of")
public class FatherService {

    public static final String FAMILY_HAVE_FATHER_STATEMENT = "family_already_have_one_father";
    public static final String FAMILY_HAVE_MOTHER_STATEMENT = "family_already_have_one_mother";
    public static final String ALREADY_EXISTING_FATHER_STATEMENT = "new_father_cannot_already_exist";
    public static final String ALREADY_EXISTING_MOTHER_STATEMENT = "new_mother_cannot_already_exist";
    private final FatherRepository fatherRepository;
    private final FamilyRepository familyRepository;

    public Father addFather(long familyId, Father father) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(businessException(FATHER_NOT_FOUND_STATEMENT))
                .checkIsFatherExist();
        father.checkIsFatherHaveUnexpectedId();
        father.setFamily(family);
        return fatherRepository.save(father);
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