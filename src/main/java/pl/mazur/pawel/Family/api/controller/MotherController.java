package pl.mazur.pawel.Family.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.MotherDto;
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
    public final MotherService service;
    public final MotherMapper mapper;

    @PostMapping("/{familyId}/mother")
    @ApiOperation(value = "Add mother")
    public Resource<MotherDto> addMother(@PathVariable long familyId, @RequestBody MotherDto motherDto) {
        var mother = mapper.map(motherDto);
        var addedMother = mapper.map(service.addMother(familyId, mother));
        log.info("Added mother with id : {}", addedMother.getId());
        return link(addedMother);
    }

    @GetMapping("/mother/{id}")
    @ApiOperation(value = "Read mother")
    public Resource<MotherDto> readMother(@PathVariable long id) {
        var foundMother = mapper.map(service.readMother(id));
        log.info("Added mother with id : {}", foundMother.getId());
        return link(foundMother);
    }

    @PutMapping("/mother")
    @ApiOperation(value = "Update mother")
    public Resource<MotherDto> updateMother(@RequestBody MotherDto motherDto) {
        var mother = mapper.map(motherDto);
        var updatedMother = mapper.map(service.updateMother(mother));
        return link(updatedMother);
    }

    @DeleteMapping("/mother/{id}")
    @ApiOperation(value = "Delete mother")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMother(@PathVariable long id) {
        service.deleteMother(id);
        log.info("Deleted mother with id : {}", id);
    }

    private Resource<MotherDto> link(MotherDto motherDto) {
        var link = (linkTo(FamilyController.class).slash(motherDto.getId()).withRel(REL));
        return new Resource<>(motherDto, link);
    }
}
