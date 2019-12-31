package pl.mazur.pawel.Family.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.mazur.pawel.Family.api.dto.FatherDto;
import pl.mazur.pawel.Family.domain.Father;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-01T00:07:13+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Private Build)"
)
@Component
public class FatherMapperImpl implements FatherMapper {

    @Override
    public Father map(FatherDto fatherDto) {
        if ( fatherDto == null ) {
            return null;
        }

        Father father = new Father();

        father.setId( fatherDto.getId() );
        father.setBirthDate( fatherDto.getBirthDate() );
        father.setFirstName( fatherDto.getFirstName() );
        father.setPesel( fatherDto.getPesel() );
        father.setSurName( fatherDto.getSurName() );

        return father;
    }

    @Override
    public FatherDto map(Father father) {
        if ( father == null ) {
            return null;
        }

        FatherDto fatherDto = new FatherDto();

        fatherDto.setId( father.getId() );
        fatherDto.setBirthDate( father.getBirthDate() );
        fatherDto.setFirstName( father.getFirstName() );
        fatherDto.setPesel( father.getPesel() );
        fatherDto.setSurName( father.getSurName() );

        return fatherDto;
    }
}
