package pl.mazur.pawel.Family.api.controller;

import com.google.common.collect.Collections2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.domain.Child;
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
    private static final String ADD_CHILD_URL = "{familyId}/child";
    private static final String READ_CHILD_URL = "/child/{id}";
    private static final String READ_CHILDS_URL = "/{familyId}/childs";
    private static final String UPDATE_CHILD_URL = "/child";
    private static final String DELETE_CHILD_URL = "/child/{id}";
    private final ChildService service;
    private final ChildMapper mapper;

    @PostMapping(ADD_CHILD_URL)
    @ApiOperation(value = "Add Child")
    public Resource<ChildDto> addChild(@PathVariable long familyId, @RequestBody ChildDto childDto) {
        Child child = mapper.map(childDto);
        ChildDto addedChild = mapper.map(service.addChild(familyId, child));
        log.info("Added child with id : {}", addedChild.getId());

        return new Resource<>(addedChild, link(addedChild.getId()));
    }

    @ApiOperation(value = "Read child")
    @GetMapping(READ_CHILD_URL)
    public Resource<ChildDto> readChild(@PathVariable long id) {
        ChildDto foundChild = mapper.map(service.readChild(id));
        log.info("Found child with id : {}", foundChild.getId());

        return new Resource<>(foundChild, link(foundChild.getId()));
    }

    @ApiOperation(value = "Read child list ")
    @GetMapping(READ_CHILDS_URL)
    public List<ChildDto> readChilds(@PathVariable long familyId) {
        List<ChildDto> foundChilds = service.readChilds(familyId)
                .stream()
                .map(mapper::map)
                .collect(toList());

        log.info("Found child's with id's : {}", Collections2.transform(foundChilds, ChildDto::getId));

        return foundChilds;
    }

    @PutMapping(UPDATE_CHILD_URL)
    @ApiOperation(value = "Update child")
    public Resource<ChildDto> updateChild(@RequestBody ChildDto childDto) {
        Child child = mapper.map(childDto);
        ChildDto updatedChild = mapper.map(service.updateChild(child));
        log.info("Updated child with id: {}", updatedChild.getId());

        return new Resource<>(updatedChild, link(updatedChild.getId()));
    }

    @DeleteMapping(DELETE_CHILD_URL)
    @ApiOperation(value = "Delete child")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChild(@PathVariable Long id) {
        service.deleteChild(id);
        log.info("Deleted child with id : {}", id);
    }

    private Link link(Object childId) {
        return linkTo(FamilyController.class).slash("/child/" + childId).withRel(REL);
    }
}
