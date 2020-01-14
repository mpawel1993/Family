package pl.mazur.pawel.Family.api.controller;

import com.google.common.collect.Collections2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.FamilyDto;
import pl.mazur.pawel.Family.api.dto.FamilySearchCriteriaDto;
import pl.mazur.pawel.Family.mapper.FamilyMapper;
import pl.mazur.pawel.Family.service.FamilyService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@Api(tags = "Family API")
@RequestMapping(value = FAMILY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyController {

    static final String FAMILY_URL = "/family";
    private static final String REL = "familyId";
    private final FamilyService service;
    private final FamilyMapper mapper;

    @GetMapping("/create")
    @ApiOperation(value = "Create family")
    public Resource<FamilyDto> createFamily() {
        var createdFamily = mapper.map(service.createFamily());
        log.info("Created family with id : {}", createdFamily.getId());
        return link(createdFamily);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Read family")
    public Resource<FamilyDto> readFamily(@PathVariable Long id) {
        var readFamily = mapper.map(service.readFamily(id));
        log.info("Was read family with id : {}", readFamily.getId());
        return link(readFamily);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete family")
    public void deleteFamily(@PathVariable Long id) {
        service.deleteFamily(id);
        log.info("Deleted family with id : {}", id);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.MULTI_STATUS)
    @ApiOperation(value = "Search family basing on family members properties")
    public List<FamilyDto> searchFamily(@RequestBody FamilySearchCriteriaDto criteriaDto) {
        var foundFamilies = service.searchFamilies(mapper.map(criteriaDto))
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
        log.info("Found families with id's : {}", Collections2.transform(foundFamilies, FamilyDto::getId));
        return foundFamilies;
    }

    private Resource<FamilyDto> link(FamilyDto familyDto) {
        var link = (linkTo(FamilyController.class).slash(familyDto.getId()).withRel(REL));
        return new Resource<>(familyDto, link);
    }
}