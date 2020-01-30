package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.EntitySbertest;
import psychotest.service.SourceService;

import java.util.List;

@RestController
public class SourceController {

    @Autowired
    public SourceService sourceService;

    @GetMapping("/source")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getSourceData() {
        return sourceService.getAll();
    }

    @PostMapping(value = "/source")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeSourceData(@RequestBody List<EntitySbertest> customObjects) {
        sourceService.saveAll(customObjects);
    }

}