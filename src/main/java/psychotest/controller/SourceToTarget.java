package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;
import psychotest.repository.TargetRepository;

import java.util.Date;
import java.util.List;

@RestController
public class SourceToTarget {

    @Autowired
    public TargetRepository targetRepository;

    @Autowired
    public SourceRepository sourceRepository;

    //insert into database2 data from database1
    //@Scheduled(fixedRate = 3000)
    @Scheduled(cron = "0 0 0 * * 5")
    @GetMapping(value = "/sbertest2-to-sbertest1")
    @ResponseStatus(HttpStatus.OK)
    private void storeSourceToTarget() {
        Date date1 = new Date();
        List<EntitySbertest> customers = sourceRepository.getDataSinceCurrentDate(date1);
        targetRepository.saveDataCall(customers);
    }

    //get values from database2 since enter date
    @GetMapping("/sbertest-data-since-current-time")
    @ResponseStatus(HttpStatus.OK)
    public List<EntitySbertest> getSourceDataSinceCurrentTime() {
        Date date = new Date();
        List<EntitySbertest> customers = sourceRepository.getDataSinceCurrentDate(date);
        return customers;
    }

}