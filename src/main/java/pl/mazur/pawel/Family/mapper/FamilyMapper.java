package pl.mazur.pawel.Family.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mazur.pawel.Family.api.dto.FamilyDto;
import pl.mazur.pawel.Family.api.dto.FamilySearchCriteriaDto;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.FamilySearchCriteria;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    @Mapping(target = "father", source = "fatherDto")
    @Mapping(target = "mother", source = "motherDto")
    @Mapping(target = "childs", source = "childsDtos")
    Family map(FamilyDto familyDto);

    @Mapping(target = "fatherDto", source = "father")
    @Mapping(target = "motherDto", source = "mother")
    @Mapping(target = "childsDtos", source = "childs")
    FamilyDto map(Family family);

    FamilySearchCriteriaDto map(FamilySearchCriteria familySearchCriteria);

    FamilySearchCriteria map(FamilySearchCriteriaDto familySearchCriteriaDto);
}
