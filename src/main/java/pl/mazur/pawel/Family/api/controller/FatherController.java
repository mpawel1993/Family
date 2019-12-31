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
import pl.mazur.pawel.Family.api.dto.FatherDto;
import pl.mazur.pawel.Family.domain.Father;
import pl.mazur.pawel.Family.mapper.FatherMapper;
import pl.mazur.pawel.Family.service.FatherService;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL;

@Slf4j
@Valid
@RestController
@AllArgsConstructor
@Api(tags = "Father API")
@RequestMapping(value = FAMILY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FatherController {

    public static final String ADD_FATHER_URL = "/{familyId}/father";
    private static final String REL = "fatherId";
    private static final String READ_FATHER_URL = "/father/{id}";
    private static final String UPDATE_FATHER_URL = "/father";
    private static final String DELETE_FATHER_URL = "/{id}/father";
    public final FatherService service;
    public final FatherMapper mapper;

    @PostMapping("/{familyId}/father")
    @ApiOperation(value = "Add father")
    public Resource<FatherDto> addFather(@PathVariable long familyId, @RequestBody FatherDto fatherDto) {
        Father father = mapper.map(fatherDto);
        FatherDto addedFather = mapper.map(service.addFather(familyId, father));
        log.info("Added father with id : {}", addedFather.getId());

        return new Resource<>(addedFather, link(addedFather.getId()));
    }

    @GetMapping(READ_FATHER_URL)
    @ApiOperation(value = "Read father")
    public Resource<FatherDto> readFather(@PathVariable long id) {
        FatherDto readFather = mapper.map(service.readFather(id));
        log.info("Was read father with id : {}", readFather.getId());

        return new Resource<>(readFather, link(readFather.getId()));
    }

    @PutMapping(UPDATE_FATHER_URL)
    @ApiOperation(value = "Update father")
    public Resource<FatherDto> updateFather(@RequestBody FatherDto fatherDto) {
        Father father = mapper.map(fatherDto);
        FatherDto updatedFather = mapper.map(service.updateFather(father));

        log.info("Updated father with id : {}", updatedFather.getId());

        return new Resource<>(updatedFather, link(updatedFather.getId()));
    }

    @DeleteMapping(DELETE_FATHER_URL)
    @ApiOperation(value = "Delete father")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFather(@PathVariable long id) {
        service.deleteFather(id);
        log.info("Deleted father with id : {}", id);
    }

    private Link link(long fatherId) {
        return linkTo(FatherController.class).slash("/father/" + fatherId).withRel(REL);
    }
}
