package psychotest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainController {
    @Value("${welcome.message}")
    private String message = "Hello World";

    // Обычно я использую интерфейс Model, но в целом разницы нет,
    // т.к. используется реализация LinkedHashMap(Key, Val)
    @RequestMapping("/2122")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "hello";
    }
    @RequestMapping(value = "/2121", method = GET)
    public String welcome() {
        return "index";
    }

    @RequestMapping(value = "/2123")
    public String auth() {
        return "auth";
    }
}
