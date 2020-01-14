package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import psychotest.database2.entity.SberTest2;
import psychotest.repository.Database2Repository;
import psychotest.repository.Database2ToDatabase1Repository;

import java.util.Date;
import java.util.List;

@RestController
public class Database2ToDadabase1Controller {

    @Autowired
    public Database2ToDatabase1Repository database2ToDatabase1Repository;

    @Autowired
    public Database2Repository database2Repository;

    //insert into database2 data from database1
    //@Scheduled(fixedRate = 3000)
    @Scheduled(cron = "0 0 0 * * 5")
    @GetMapping(value = "/sbertest2-to-sbertest1")
    @ResponseStatus(HttpStatus.OK)
    private void storeData2() {
        Date date1 = new Date();
        List<SberTest2> customers = database2Repository.getDataList2SinceCurrentDate(date1);
        database2ToDatabase1Repository.saveBatch(customers);
    }

    //get values from database2 since enter date
    @GetMapping("/sbertest222")
    @ResponseStatus(HttpStatus.OK)
    public List<SberTest2> customerInformation2() {
        Date date = new Date();
        List<SberTest2> customers = database2Repository.getDataList2SinceCurrentDate(date);
        return customers;
    }

}