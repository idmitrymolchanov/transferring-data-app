package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psychotest.database1.entity.SberTest1;
import psychotest.repository.Database1Repository;

import java.util.List;

@RestController
public class Database1Controller {

    @Autowired
    public Database1Repository database1Repository;

    //get values from database1
    @GetMapping("/sbertest1")
    @ResponseStatus(HttpStatus.OK)
    public List<SberTest1> customerInformation() {
        List<SberTest1> customers = database1Repository.isData();
        return customers;
    }

    //insert values into database1
    @PostMapping(value = "/sbertest1")
    @ResponseStatus(HttpStatus.CREATED)
    private void storeData(@RequestBody List<SberTest1> customObjects) {
        database1Repository.saveBatch(customObjects);
    }
}
