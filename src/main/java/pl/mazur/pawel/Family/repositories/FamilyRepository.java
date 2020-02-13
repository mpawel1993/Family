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

    @Query(value = "select distinct * from family " +
            "inner join father f on family.father_id = f.id " +
            "inner join mother m on family.mother_id = m.id " +
            "inner join child c2 on family.id = c2.childs_id", nativeQuery = true)
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
