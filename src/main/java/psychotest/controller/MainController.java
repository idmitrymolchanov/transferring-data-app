package psychotest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainController {
    @Value("${welcome.message}")
    private String message = "Hello World";

    @RequestMapping("/2122")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "hello";
    }
    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "realindex";
    }

    @RequestMapping(value = "/2123")
    public String auth() {
        return "auth";
    }
}
