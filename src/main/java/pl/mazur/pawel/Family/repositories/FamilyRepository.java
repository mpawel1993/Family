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
            "inner join father fat on family.father_id = fat.id " +
            "inner join mother mat on family.mother_id = mat.id " +
            "inner join child ch on family.id = ch.childs_id where " +
            "fat.first_name  like %:fatherFirstName% or " +
            "fat.sur_name like %:fatherSurName% or " +
            "fat.pesel like %:fatherPesel% or " +
            "fat.birth_date = :fatherBirthDate or " +
            "mat.first_name  like %:motherFirstName% or " +
            "mat.sur_name like %:motherSurName% or " +
            "mat.pesel like %:motherPesel% or " +
            "mat.birth_date = :motherBirthDate or " +
            "ch.first_name like %:childFirstName% or " +
            "ch.sur_name like %:childSurName% or " +
            "ch.pesel like %:childPesel% or " +
            "ch.sex = :childSex or " +
            "ch.birth_date = :childBirthDate"
            , nativeQuery = true)
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