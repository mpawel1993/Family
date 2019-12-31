package pl.mazur.pawel.Family.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

//@Builder(toBuilder = true)
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(NON_NULL)
public class FamilyDto implements Serializable {

    private Long id;
    private FatherDto fatherDto;
    private MotherDto motherDto;
    private List<ChildDto> childsDtos;
}
