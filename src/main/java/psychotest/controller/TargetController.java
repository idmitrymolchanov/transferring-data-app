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

    @GetMapping("/target")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getTargetData() {
        return targetService.getAll();
    }

    @PostMapping(value = "/target")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeTargetData(@RequestBody List<EntitySbertest> customObjects) {
        targetService.saveAll(customObjects);
    }

    @GetMapping(value = "/target/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getAllById(@PathVariable Long id){
        return targetService.getAllById(id);
    }
}
