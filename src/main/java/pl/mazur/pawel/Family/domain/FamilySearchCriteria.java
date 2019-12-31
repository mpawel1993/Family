package pl.mazur.pawel.Family.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilySearchCriteria {

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDate fatherBirthDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDate motherBirthDate;
    String childName;
    String childSurName;
    String childPesel;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDate childBirthDay;
    ChildSex childSex;
    private String fatherFirstName;
    private String fatherPesel;
    private String fatherSurName;
    private String motherFirstName;
    private String motherPesel;
    private String motherSurName;
}
