package pl.mazur.pawel.Family.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.MotherDto;
import pl.mazur.pawel.Family.domain.Mother;
import pl.mazur.pawel.Family.mapper.MotherMapper;
import pl.mazur.pawel.Family.service.MotherService;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL;

@Slf4j
@Valid
@RestController
@AllArgsConstructor
@Api(tags = "Mother API")
@RequestMapping(value = FAMILY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MotherController {

    private static final String REL = "motherId";
    private static final String ADD_MOTHER_URL = "/{familyId}/mother";
    private static final String READ_MOTHER_URL = "/mother/{id}";
    private static final String UPDATE_MOTHER_URL = "/mother";
    private static final String DELETE_MOTHER_URL = "/mother/{id}";
    public final MotherService service;
    public final MotherMapper mapper;

    @PostMapping(ADD_MOTHER_URL)
    @ApiOperation(value = "Add mother")
    public Resource<MotherDto> addMother(@PathVariable long familyId, @RequestBody MotherDto motherDto) {
        Mother mother = mapper.map(motherDto);
        MotherDto addedMother = mapper.map(service.addMother(familyId, mother));
        log.info("Added mother with id : {}", addedMother.getId());

        return new Resource<>(addedMother, link(addedMother.getId()));
    }

    @GetMapping(READ_MOTHER_URL)
    @ApiOperation(value = "Read mother")
    public Resource<MotherDto> readMother(@PathVariable long id) {
        MotherDto foundMother = mapper.map(service.readMother(id));
        log.info("Added mother with id : {}", foundMother.getId());

        return new Resource<>(foundMother, link(foundMother.getId()));
    }

    @PutMapping(UPDATE_MOTHER_URL)
    @ApiOperation(value = "Update mother")
    public Resource<MotherDto> updateMother(@RequestBody MotherDto motherDto) {
        Mother mother = mapper.map(motherDto);
        MotherDto updatedMother = mapper.map(service.updateMother(mother));

        return new Resource<>(updatedMother, link(updatedMother.getId()));
    }

    @DeleteMapping(DELETE_MOTHER_URL)
    @ApiOperation(value = "Delete mother")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMother(@PathVariable long id) {
        service.deleteMother(id);
        log.info("Deleted mother with id : {}", id);
    }

    private Link link(long motherId) {
        return linkTo(MotherController.class).slash("/mother/" + motherId).withRel(REL);
    }
}
