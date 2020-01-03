package pl.mazur.pawel.Family.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.FatherDto;
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

    private static final String REL = "fatherId";
    public final FatherService service;
    public final FatherMapper mapper;

    @PostMapping("/{familyId}/father")
    @ApiOperation(value = "Add father")
    public Resource<FatherDto> addFather(@PathVariable long familyId, @RequestBody FatherDto fatherDto) {
        var father = mapper.map(fatherDto);
        var addedFather = mapper.map(service.addFather(familyId, father));
        log.info("Added father with id : {}", addedFather.getId());
        return link(addedFather);
    }

    @GetMapping("/father/{id}")
    @ApiOperation(value = "Read father")
    public Resource<FatherDto> readFather(@PathVariable long id) {
        var readFather = mapper.map(service.readFather(id));
        log.info("Was read father with id : {}", readFather.getId());
        return link(readFather);
    }

    @PutMapping("/father")
    @ApiOperation(value = "Update father")
    public Resource<FatherDto> updateFather(@RequestBody FatherDto fatherDto) {
        var father = mapper.map(fatherDto);
        var updatedFather = mapper.map(service.updateFather(father));
        log.info("Updated father with id : {}", updatedFather.getId());
        return link(updatedFather);
    }

    @DeleteMapping("/{id}/father")
    @ApiOperation(value = "Delete father")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFather(@PathVariable long id) {
        service.deleteFather(id);
        log.info("Deleted father with id : {}", id);
    }

    private Resource<FatherDto> link(FatherDto fatherDto) {
        var link = (linkTo(FamilyController.class).slash("/child/" + fatherDto.getId()).withRel(REL));
        return new Resource<>(fatherDto, link);
    }
}
