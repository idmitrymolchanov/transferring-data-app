package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.util.List;

@RestController
public class TargetController {

    @Autowired
    public TargetRepository targetRepository;

    //get values from database1
    @GetMapping("/sbertest1")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> customerInformation() {
        List<EntitySbertest> customers = targetRepository.getDataCall();
        return customers;
    }

    @PostMapping(value = "/sbertest1")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeData(@RequestBody List<EntitySbertest> customObjects) {
        targetRepository.saveDataCall(customObjects);
    }

}
