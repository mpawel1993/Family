package pl.mazur.pawel.Family.mapper;

import org.mapstruct.Mapper;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.domain.Child;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildDto map(Child child);

    Child map(ChildDto childDto);
}
