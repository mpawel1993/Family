package pl.mazur.pawel.Family.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mazur.pawel.Family.api.dto.ChildDto;
import pl.mazur.pawel.Family.mapper.ChildMapper;
import pl.mazur.pawel.Family.service.ChildService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.mazur.pawel.Family.api.controller.FamilyController.FAMILY_URL;

@Slf4j
@Valid
@RestController
@AllArgsConstructor
@Tag(name = "Child API")
@RequestMapping(value = FAMILY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChildController {

    private static final String REL = "childId";
    private final ChildService service;
    private final ChildMapper mapper;

    @PostMapping("{familyId}/child")
    @Operation(summary = "Add Child")
    public ChildDto addChild(@PathVariable Long familyId, @RequestBody ChildDto childDto) {
        var child = mapper.map(childDto);
        var addedChild = mapper.map(service.addChild(familyId, child));
        log.info("Added child with id : {}", addedChild.getId());
        return addedChild;
    }

    @Operation(summary = "Read child")
    @GetMapping("/child/{id}")
    public ChildDto readChild(@PathVariable long id) {
        var foundChild = mapper.map(service.readChild(id));
        log.info("Found child with id : {}", foundChild.getId());
        return foundChild;
    }

    @Operation(summary = "Read child list ")
    @GetMapping("/{familyId}/childs")
    public List<ChildDto> readChilds(@PathVariable long familyId) {
        List<ChildDto> foundChilds = service.readChilds(familyId)
                .stream()
                .map(mapper::map)
                .collect(toList());
        log.info("Found child's with id's : {}", foundChilds.stream().map(ChildDto::getId).collect(toList()));
        return foundChilds;
    }

    @PutMapping("/child")
    @Operation(summary = "Update child")
    public ChildDto updateChild(@RequestBody ChildDto childDto) {
        var child = mapper.map(childDto);
        var updatedChild = mapper.map(service.updateChild(child));
        log.info("Updated child with id: {}", updatedChild.getId());
        return updatedChild;
    }

    @DeleteMapping("/child/{id}")
    @Operation(summary = "Delete child")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChild(@PathVariable Long id) {
        service.deleteChild(id);
        log.info("Deleted child with id : {}", id);
    }
}
