package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.EntitySbertest;
import psychotest.service.SourceService;
import psychotest.service.TargetService;

import java.util.List;

@RestController
public class SourceController {

    @Autowired
    public SourceService sourceService;

    @Autowired
    public TargetService targetService;

    @GetMapping(value = "/source/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> findById(@PathVariable Long id){
        return sourceService.findById(id);
    }

    @PostMapping(value = "/source")
    @ResponseStatus(HttpStatus.CREATED)
    private void saveAll(@RequestBody List<EntitySbertest> customObjects) {
        sourceService.save(customObjects);
    }

    @GetMapping("/source-since-last-target-date")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> findAllSinceLastTargetDate() {
        List<EntitySbertest> entitySbertestList = sourceService.findAllSinceLastTargetDate(targetService.getLastDate());
        return entitySbertestList;
    }
}