package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import psychotest.service.UserListService;

@Controller
public class UserListController {
    private final UserListService service;

    @Autowired
    public UserListController(UserListService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showListUser(ModelMap modelMap) {
        modelMap.put("todos", service.getAllUsers());
        return "users";
    }
}
