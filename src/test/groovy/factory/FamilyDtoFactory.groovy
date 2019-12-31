package factory

import pl.mazur.pawel.Family.api.dto.*

import java.time.LocalDate

class FamilyDtoFactory {

    static FatherDto createFatherDto() {
        FatherDto.builder()
                .id(1L)
                .birthDate(LocalDate.parse('1965-10-01'))
                .firstName('Roman')
                .pesel('5913203098')
                .surName('Nowak')
                .build()
    }

    static MotherDto createMotherDto() {
        MotherDto.builder()
                .id(1L)
                .birthDate(LocalDate.parse('1965-10-01'))
                .firstName('Anna')
                .pesel('6510013098')
                .surName('Nowak')
                .build()
    }

    static ChildDto createChildDto() {
        ChildDto.builder()
                .id(1L)
                .pesel('010315987')
                .firstName('Wojtek')
                .surName('Nowak')
                .birthDate(LocalDate.parse('2001-03-15'))
                .sex(ChildSexDto.MALE)
                .build()
    }

    static FamilySearchCriteriaDto createCriteriaDto() {
        FamilySearchCriteriaDto.builder()
                .fatherFirstName('Roman')
                .fatherSurName('Nowak')
                .childName('Zosia')
                .build()
    }

    static FamilyDto createFamilyDto() {
        FamilyDto.builder()
                .id(1L)
                .fatherDto(FatherDto.builder().id(1L).build())
                .motherDto(MotherDto.builder().id(1L).build())
                .build()
    }

    static FamilyDto updatedFamilyDto() {
        FamilyDto.builder()
                .id(123L)
                .fatherDto(FatherDto.builder().id(2L).firstName('updatedName').build())
                .motherDto(MotherDto.builder().id(2L).firstName('updatedName').build())
                .build()
    }

    static List<FamilyDto> createFamilyDtoList() {
        [createFamilyDto().toBuilder().id(2L).build(),
         createFamilyDto().toBuilder().id(3L).build()]
    }

    static FamilySearchCriteriaDto createSearchFamilyCriteriaDto() {
        FamilySearchCriteriaDto.builder()
                .fatherFirstName('Tomasz')
                .fatherSurName('Kowalski')
                .motherFirstName('Aneta')
                .childName('Alicja')
                .build()
    }
}
