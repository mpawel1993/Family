package pl.mazur.pawel.Family.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.FatherDto;
import pl.mazur.pawel.Family.mapper.FatherMapper;
import pl.mazur.pawel.Family.service.FatherService;

import javax.validation.Valid;

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
    @ApiOperation("Add father")
    public FatherDto addFather(@PathVariable long familyId, @RequestBody FatherDto fatherDto) {
        var father = mapper.map(fatherDto);
        var addedFather = mapper.map(service.addFather(familyId, father));
        log.info("Added father with id : {}", addedFather.getId());
        return addedFather;
    }

    @GetMapping("/father/{id}")
    @ApiOperation("Read father")
    public FatherDto readFather(@PathVariable Long id) {
        var readFather = mapper.map(service.readFather(id));
        log.info("Was read father with id : {}", readFather.getId());
        return readFather;
    }

    @PutMapping("/father")
    @ApiOperation("Update father")
    public FatherDto updateFather(@RequestBody FatherDto fatherDto) {
        var father = mapper.map(fatherDto);
        var updatedFather = mapper.map(service.updateFather(father));
        log.info("Updated father with id : {}", updatedFather.getId());
        return updatedFather;
    }

    @DeleteMapping("/{id}/father")
    @ApiOperation("Delete father")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFather(@PathVariable long id) {
        service.deleteFather(id);
        log.info("Deleted father with id : {}", id);
    }
}
