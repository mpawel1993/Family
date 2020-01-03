package pl.mazur.pawel.Family.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.mazur.pawel.Family.api.dto.MotherDto;
import pl.mazur.pawel.Family.domain.Mother;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-03T12:17:45+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Private Build)"
)
@Component
public class MotherMapperImpl implements MotherMapper {

    @Override
    public MotherDto map(Mother mother) {
        if ( mother == null ) {
            return null;
        }

        MotherDto motherDto = new MotherDto();

        motherDto.setId( mother.getId() );
        motherDto.setBirthDate( mother.getBirthDate() );
        motherDto.setFirstName( mother.getFirstName() );
        motherDto.setPesel( mother.getPesel() );
        motherDto.setSurName( mother.getSurName() );

        return motherDto;
    }

    @Override
    public Mother map(MotherDto motherDto) {
        if ( motherDto == null ) {
            return null;
        }

        Mother mother = new Mother();

        mother.setId( motherDto.getId() );
        mother.setBirthDate( motherDto.getBirthDate() );
        mother.setFirstName( motherDto.getFirstName() );
        mother.setPesel( motherDto.getPesel() );
        mother.setSurName( motherDto.getSurName() );

        return mother;
    }
}
