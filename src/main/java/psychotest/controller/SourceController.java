package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;

import java.util.List;

@RestController
public class SourceController {

    @Autowired
    public SourceRepository sourceRepository;

    //get values from database2
    @GetMapping("/sbertest2")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getSourceData() {
        List<EntitySbertest> customers = sourceRepository.getDataCall();
        return customers;
    }

    //insert values into database2
    @PostMapping(value = "/sbertest2")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeSourceData(@RequestBody List<EntitySbertest> customObjects) {
        sourceRepository.saveDataCall(customObjects);
    }

}