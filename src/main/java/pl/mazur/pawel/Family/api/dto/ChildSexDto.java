package pl.mazur.pawel.Family.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public enum ChildSexDto {
    MALE, FEMALE
}
