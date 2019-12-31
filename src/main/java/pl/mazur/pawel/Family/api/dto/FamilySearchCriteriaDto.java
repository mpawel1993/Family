package pl.mazur.pawel.Family.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class FamilySearchCriteriaDto {

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate fatherBirthDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate motherBirthDate;
    private String childName;
    private String childSurName;
    private String childPesel;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate childBirthDay;
    private ChildSexDto childSex;
    private String fatherFirstName;
    private String fatherPesel;
    private String fatherSurName;
    private String motherFirstName;
    private String motherPesel;
    private String motherSurName;
}
