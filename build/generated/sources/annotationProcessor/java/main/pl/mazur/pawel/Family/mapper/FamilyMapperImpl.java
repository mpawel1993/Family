package pl.mazur.pawel.Family.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.api.dto.ChildSexDto;
import pl.mazur.pawel.Family.api.dto.FamilyDto;
import pl.mazur.pawel.Family.api.dto.FamilySearchCriteriaDto;
import pl.mazur.pawel.Family.api.dto.FatherDto;
import pl.mazur.pawel.Family.api.dto.MotherDto;
import pl.mazur.pawel.Family.domain.Child;
import pl.mazur.pawel.Family.domain.ChildSex;
import pl.mazur.pawel.Family.domain.Family;
import pl.mazur.pawel.Family.domain.FamilySearchCriteria;
import pl.mazur.pawel.Family.domain.Father;
import pl.mazur.pawel.Family.domain.Mother;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-01T00:07:12+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Private Build)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public Family map(FamilyDto familyDto) {
        if ( familyDto == null ) {
            return null;
        }

        Family family = new Family();

        family.setMother( motherDtoToMother( familyDto.getMotherDto() ) );
        family.setChilds( childDtoListToChildList( familyDto.getChildsDtos() ) );
        family.setFather( fatherDtoToFather( familyDto.getFatherDto() ) );
        family.setId( familyDto.getId() );

        return family;
    }

    @Override
    public FamilyDto map(Family family) {
        if ( family == null ) {
            return null;
        }

        FamilyDto familyDto = new FamilyDto();

        familyDto.setMotherDto( motherToMotherDto( family.getMother() ) );
        familyDto.setChildsDtos( childListToChildDtoList( family.getChilds() ) );
        familyDto.setFatherDto( fatherToFatherDto( family.getFather() ) );
        familyDto.setId( family.getId() );

        return familyDto;
    }

    @Override
    public FamilySearchCriteriaDto map(FamilySearchCriteria familySearchCriteria) {
        if ( familySearchCriteria == null ) {
            return null;
        }

        FamilySearchCriteriaDto familySearchCriteriaDto = new FamilySearchCriteriaDto();

        familySearchCriteriaDto.setFatherBirthDate( familySearchCriteria.getFatherBirthDate() );
        familySearchCriteriaDto.setMotherBirthDate( familySearchCriteria.getMotherBirthDate() );
        familySearchCriteriaDto.setChildName( familySearchCriteria.getChildName() );
        familySearchCriteriaDto.setChildSurName( familySearchCriteria.getChildSurName() );
        familySearchCriteriaDto.setChildPesel( familySearchCriteria.getChildPesel() );
        familySearchCriteriaDto.setChildBirthDay( familySearchCriteria.getChildBirthDay() );
        familySearchCriteriaDto.setChildSex( childSexToChildSexDto( familySearchCriteria.getChildSex() ) );
        familySearchCriteriaDto.setFatherFirstName( familySearchCriteria.getFatherFirstName() );
        familySearchCriteriaDto.setFatherPesel( familySearchCriteria.getFatherPesel() );
        familySearchCriteriaDto.setFatherSurName( familySearchCriteria.getFatherSurName() );
        familySearchCriteriaDto.setMotherFirstName( familySearchCriteria.getMotherFirstName() );
        familySearchCriteriaDto.setMotherPesel( familySearchCriteria.getMotherPesel() );
        familySearchCriteriaDto.setMotherSurName( familySearchCriteria.getMotherSurName() );

        return familySearchCriteriaDto;
    }

    @Override
    public FamilySearchCriteria map(FamilySearchCriteriaDto familySearchCriteriaDto) {
        if ( familySearchCriteriaDto == null ) {
            return null;
        }

        FamilySearchCriteria familySearchCriteria = new FamilySearchCriteria();

        familySearchCriteria.setFatherBirthDate( familySearchCriteriaDto.getFatherBirthDate() );
        familySearchCriteria.setMotherBirthDate( familySearchCriteriaDto.getMotherBirthDate() );
        familySearchCriteria.setChildName( familySearchCriteriaDto.getChildName() );
        familySearchCriteria.setChildSurName( familySearchCriteriaDto.getChildSurName() );
        familySearchCriteria.setChildPesel( familySearchCriteriaDto.getChildPesel() );
        familySearchCriteria.setChildBirthDay( familySearchCriteriaDto.getChildBirthDay() );
        familySearchCriteria.setChildSex( childSexDtoToChildSex( familySearchCriteriaDto.getChildSex() ) );
        familySearchCriteria.setFatherFirstName( familySearchCriteriaDto.getFatherFirstName() );
        familySearchCriteria.setFatherPesel( familySearchCriteriaDto.getFatherPesel() );
        familySearchCriteria.setFatherSurName( familySearchCriteriaDto.getFatherSurName() );
        familySearchCriteria.setMotherFirstName( familySearchCriteriaDto.getMotherFirstName() );
        familySearchCriteria.setMotherPesel( familySearchCriteriaDto.getMotherPesel() );
        familySearchCriteria.setMotherSurName( familySearchCriteriaDto.getMotherSurName() );

        return familySearchCriteria;
    }

    protected Mother motherDtoToMother(MotherDto motherDto) {
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

    protected Child childDtoToChild(ChildDto childDto) {
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

    protected List<Child> childDtoListToChildList(List<ChildDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Child> list1 = new ArrayList<Child>( list.size() );
        for ( ChildDto childDto : list ) {
            list1.add( childDtoToChild( childDto ) );
        }

        return list1;
    }

    protected Father fatherDtoToFather(FatherDto fatherDto) {
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

    protected MotherDto motherToMotherDto(Mother mother) {
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

    protected ChildDto childToChildDto(Child child) {
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

    protected List<ChildDto> childListToChildDtoList(List<Child> list) {
        if ( list == null ) {
            return null;
        }

        List<ChildDto> list1 = new ArrayList<ChildDto>( list.size() );
        for ( Child child : list ) {
            list1.add( childToChildDto( child ) );
        }

        return list1;
    }

    protected FatherDto fatherToFatherDto(Father father) {
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
