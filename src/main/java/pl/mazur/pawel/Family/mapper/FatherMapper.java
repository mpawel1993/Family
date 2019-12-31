package pl.mazur.pawel.Family.mapper;

import org.mapstruct.Mapper;
import pl.mazur.pawel.Family.api.dto.FatherDto;
import pl.mazur.pawel.Family.domain.Father;

@Mapper(componentModel = "spring")
public interface FatherMapper {

    Father map(FatherDto fatherDto);

    FatherDto map(Father father);
}
