package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import psychotest.entity.EntitySbertest;
import psychotest.service.SourceService;
import psychotest.service.TargetService;

import java.util.List;

@RestController
public class SourceToTargetController {

    @Autowired
    public TargetService targetService;

    @Autowired
    public SourceService sourceService;

    @GetMapping(value = "/source-to-target")
    @ResponseStatus(HttpStatus.OK)
    private void storeSourceToTarget() {
        List<EntitySbertest> customers = sourceService.getAllSinceCurrentDate(targetService.getLastDate());
        targetService.saveAll(customers);
    }

    @GetMapping("/sbertest-data-since-current-time")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getSourceDataSinceCurrentTime() {
        List<EntitySbertest> customers = sourceService.getAllSinceCurrentDate(targetService.getLastDate());
        return customers;
    }

}