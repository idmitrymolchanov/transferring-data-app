package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.database2.entity.SberTest2;
import psychotest.repository.Database2Repository;

import java.util.List;

@RestController
public class Database2Controller {

    @Autowired
    public Database2Repository database2Repository;

    //get values from database2
    @GetMapping("/sbertest2")
    @ResponseStatus(HttpStatus.OK)
    public List<SberTest2> customerInformation2() {
        List<SberTest2> customers = database2Repository.isData();
        return customers;
    }

    //insert values into database2
    @PostMapping(value = "/sbertest2")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeData(@RequestBody List<SberTest2> customObjects) {
        database2Repository.saveBatch(customObjects);
    }

}