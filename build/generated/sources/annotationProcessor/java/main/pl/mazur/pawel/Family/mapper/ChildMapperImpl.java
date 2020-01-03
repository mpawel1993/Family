package pl.mazur.pawel.Family.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.api.dto.ChildSexDto;
import pl.mazur.pawel.Family.domain.Child;
import pl.mazur.pawel.Family.domain.ChildSex;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-03T12:17:45+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Private Build)"
)
@Component
public class ChildMapperImpl implements ChildMapper {

    @Override
    public ChildDto map(Child child) {
        if ( child == null ) {
            return null;
        }

        ChildDto childDto = new ChildDto();

        childDto.setId( child.getId() );
        childDto.setPesel( child.getPesel() );
        childDto.setFirstName( child.getFirstName() );
        childDto.setSurName( child.getSurName() );
        childDto.setBirthDate( child.getBirthDate() );
        childDto.setSex( childSexToChildSexDto( child.getSex() ) );

        return childDto;
    }

    @Override
    public Child map(ChildDto childDto) {
        if ( childDto == null ) {
            return null;
        }

        Child child = new Child();

        child.setId( childDto.getId() );
        child.setPesel( childDto.getPesel() );
        child.setFirstName( childDto.getFirstName() );
        child.setSurName( childDto.getSurName() );
        child.setBirthDate( childDto.getBirthDate() );
        child.setSex( childSexDtoToChildSex( childDto.getSex() ) );

        return child;
    }

    protected ChildSexDto childSexToChildSexDto(ChildSex childSex) {
        if ( childSex == null ) {
            return null;
        }

        ChildSexDto childSexDto;

        switch ( childSex ) {
            case MALE: childSexDto = ChildSexDto.MALE;
            break;
            case FEMALE: childSexDto = ChildSexDto.FEMALE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + childSex );
        }

        return childSexDto;
    }

    protected ChildSex childSexDtoToChildSex(ChildSexDto childSexDto) {
        if ( childSexDto == null ) {
            return null;
        }

        ChildSex childSex;

        switch ( childSexDto ) {
            case MALE: childSex = ChildSex.MALE;
            break;
            case FEMALE: childSex = ChildSex.FEMALE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + childSexDto );
        }

        return childSex;
    }
}
