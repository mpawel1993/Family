package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mazur.pawel.Family.domain.ChildSex;
import pl.mazur.pawel.Family.domain.Family;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    Family findByFatherId(long fatherId);

    @Query("from Family family inner join family.father father on family.father.id = father.id " +
            "inner join family.mother mother on family.mother.id = mother.id " +
            "inner join family.childs childs on family.id = childs.id where " +
            "father.firstName like  :fatherFirstName% and " +
            "father.surName like :fatherSurName% and " +
            "father.pesel like  :fatherPesel% and " +
            "father.birthDate like :fatherBirthDate% and " +
            "mother.firstName like :motherFirstName% and " +
            "mother.surName like :motherSurName% and " +
            "mother.pesel like :motherPesel% and " +
            "mother.birthDate like :motherBirthDate% and " +
            "childs.firstName like :childFirstName% and " +
            "childs.surName like :childSurName% and " +
            "childs.pesel like :childPesel% and " +
            "childs.sex like :childSex% and " +
            "childs.birthDate like :childBirthDate%")
    List<Family> searchFamilies(@Param("fatherFirstName") String fatherFirstName,
                                @Param("fatherSurName") String fatherSurName,
                                @Param("fatherPesel") String fatherPesel,
                                @Param("fatherBirthDate") LocalDate fatherBirthDate,
                                @Param("motherFirstName") String motherFirstName,
                                @Param("motherSurName") String motherSurName,
                                @Param("motherPesel") String motherPesel,
                                @Param("motherBirthDate") LocalDate motherBirthDate,
                                @Param("childFirstName") String childFirstName,
                                @Param("childSurName") String childSurName,
                                @Param("childPesel") String childPesel,
                                @Param("childSex") ChildSex childSex,
                                @Param("childBirthDate") LocalDate childBirthDay);
}