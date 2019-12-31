package pl.mazur.pawel.Family.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(NON_NULL)
public class MotherDto {

    private Long id;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String pesel;

    @NotNull
    @NotBlank
    private String surName;
}
