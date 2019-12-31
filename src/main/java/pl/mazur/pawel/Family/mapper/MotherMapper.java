package pl.mazur.pawel.Family.mapper;

import org.mapstruct.Mapper;
import pl.mazur.pawel.Family.api.dto.MotherDto;
import pl.mazur.pawel.Family.domain.Mother;

@Mapper(componentModel = "spring")
public interface MotherMapper {

    MotherDto map(Mother mother);

    Mother map(MotherDto motherDto);
}
