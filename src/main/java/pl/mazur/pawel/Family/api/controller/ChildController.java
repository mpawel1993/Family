package pl.mazur.pawel.Family.api.controller;

import com.google.common.collect.Collections2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.mapper.ChildMapper;
import pl.mazur.pawel.Family.service.ChildService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL;

@Slf4j
@Valid
@RestController
@AllArgsConstructor
@Api(tags = "Child API")
@RequestMapping(value = FAMILY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChildController {

    private static final String REL = "childId";
    private final ChildService service;
    private final ChildMapper mapper;

    @PostMapping("{familyId}/child")
    @ApiOperation(value = "Add Child")
    public Resource<ChildDto> addChild(@PathVariable long familyId, @RequestBody ChildDto childDto) {
        var child = mapper.map(childDto);
        var addedChild = mapper.map(service.addChild(familyId, child));
        log.info("Added child with id : {}", addedChild.getId());
        return link(addedChild);
    }

    @ApiOperation(value = "Read child")
    @GetMapping("/child/{id}")
    public Resource<ChildDto> readChild(@PathVariable long id) {
        var foundChild = mapper.map(service.readChild(id));
        log.info("Found child with id : {}", foundChild.getId());
        return link(foundChild);
    }

    @ApiOperation(value = "Read child list ")
    @GetMapping("/{familyId}/childs")
    public List<ChildDto> readChilds(@PathVariable long familyId) {
        List<ChildDto> foundChilds = service.readChilds(familyId)
                .stream()
                .map(mapper::map)
                .collect(toList());
        log.info("Found child's with id's : {}", Collections2.transform(foundChilds, ChildDto::getId));
        return foundChilds;
    }

    @PutMapping("/child")
    @ApiOperation(value = "Update child")
    public Resource<ChildDto> updateChild(@RequestBody ChildDto childDto) {
        var child = mapper.map(childDto);
        var updatedChild = mapper.map(service.updateChild(child));
        log.info("Updated child with id: {}", updatedChild.getId());
        return link(updatedChild);
    }

    @DeleteMapping("/child/{id}")
    @ApiOperation(value = "Delete child")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChild(@PathVariable Long id) {
        service.deleteChild(id);
        log.info("Deleted child with id : {}", id);
    }

    private Resource<ChildDto> link(ChildDto childDto) {
        var link = (linkTo(FamilyController.class).slash("/child/" + childDto.getId()).withRel(REL));
        return new Resource<>(childDto, link);
    }
}
