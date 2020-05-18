package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.EntitySbertest;
import psychotest.service.TargetService;

import java.util.List;

@RestController
public class TargetController {

    @Autowired
    public TargetService targetService;

    @GetMapping(value = "/target/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> findById(@PathVariable Long id){
        return targetService.findById(id);
    }

    @PostMapping(value = "/target")
    @ResponseStatus(HttpStatus.CREATED)
    private void saveAll(@RequestBody List<EntitySbertest> customObjects) {
        targetService.saveAll(customObjects);
    }
}